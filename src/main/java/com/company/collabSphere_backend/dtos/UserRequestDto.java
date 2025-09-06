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

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String role;

    private String bio;

    // location provided as latitude & longitude
    private Double latitude;
    private Double longitude;
}