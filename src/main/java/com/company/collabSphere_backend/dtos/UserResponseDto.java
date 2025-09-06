package com.company.collabSphere_backend.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String email;
    private String name;
    private String role;
    private String bio;
    private Double latitude;
    private Double longitude;
}
