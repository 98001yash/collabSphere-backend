package com.company.collabSphere_backend.controller;

import com.company.collabSphere_backend.dtos.UserRequestDto;
import com.company.collabSphere_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto requestDto) {
        String token = authService.register(requestDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        String token = authService.login(email, password);
        return ResponseEntity.ok(token);
    }
}
