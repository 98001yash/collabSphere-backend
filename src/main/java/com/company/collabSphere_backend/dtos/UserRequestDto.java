package com.company.collabSphere_backend.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String role;   // student, faculty, mentor
    private String bio;

    @NotBlank
    @Size(min = 6)
    private String password;
}
