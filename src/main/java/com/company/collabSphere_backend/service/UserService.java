package com.company.collabSphere_backend.service;


import com.company.collabSphere_backend.dtos.UserRequestDto;
import com.company.collabSphere_backend.dtos.UserResponseDto;
import com.company.collabSphere_backend.entity.User;
import com.company.collabSphere_backend.exceptions.ResourceNotFoundException;
import com.company.collabSphere_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponseDto registerUser(UserRequestDto userRequestDto){
        log.info("Registering a new user with email: {}",userRequestDto.getEmail());

        // check if the email already exists
        if(userRepository.findByEmail(userRequestDto.getEmail()).isPresent()){
            log.error("Email {} already exists",userRequestDto.getEmail());
            throw new IllegalArgumentException("Email already exists");
        }

        // map dto to entity
        User user = modelMapper.map(userRequestDto,User.class);
        //hash Password
        user.setPasswordHash(passwordEncoder.encode(userRequestDto.getPassword()));

        // save
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with id: {}",savedUser.getId());

        // map entity -> response dto
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    public UserResponseDto getUserById(Long id){
        log.info("Fetching user by id: {}",id);

        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id "+id));
        return modelMapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto updateBio(Long id, String bio){
        log.info("Updating bio for user id: {}",id);

        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id "+id));

        user.setBio(bio);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponseDto.class);
    }
}
