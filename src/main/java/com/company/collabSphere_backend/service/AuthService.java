package com.company.collabSphere_backend.service;


import com.company.collabSphere_backend.dtos.UserRequestDto;
import com.company.collabSphere_backend.dtos.UserResponseDto;
import com.company.collabSphere_backend.entity.User;
import com.company.collabSphere_backend.exceptions.ResourceNotFoundException;
import com.company.collabSphere_backend.repository.UserRepository;
import com.company.collabSphere_backend.security.JwtService;
import com.company.collabSphere_backend.utils.GeometryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(UserRequestDto userRequestDto) {
        log.info("Registering new user with email {}", userRequestDto.getEmail());

        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setRole(userRequestDto.getRole());
        user.setBio(userRequestDto.getBio());
        user.setPasswordHash(passwordEncoder.encode(userRequestDto.getPassword()));

        if (userRequestDto.getLatitude() != null && userRequestDto.getLongitude() != null) {
            user.setLocation(GeometryUtil.createPoint(
                    userRequestDto.getLatitude(),
                    userRequestDto.getLongitude()
            ));
        }
        User saved = userRepository.save(user);
        return jwtService.generateToken(saved.getEmail(), saved.getRole().name());
    }

    public String login(String email, String rawPassword) {
        log.info("Authenticating user {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtService.generateToken(user.getEmail(), user.getRole().name());
    }
}
