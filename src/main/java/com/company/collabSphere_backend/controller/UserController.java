package com.company.collabSphere_backend.controller;

import com.company.collabSphere_backend.dtos.UserResponseDto;
import com.company.collabSphere_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        log.info("Fetching all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        log.info("Fetching  user with id: {}",id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PatchMapping("/{id}/bio")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<UserResponseDto> updateUserBio(
            @PathVariable Long id,
            @RequestBody String newBio
    ) {
        log.info("Updating bio for user ID: {}", id);
        return ResponseEntity.ok(userService.updateBio(id, newBio));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
