package com.company.collabSphere_backend.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequestDto {

    @NotBlank
    private String title;

    private String description;

    private String githubLink;

    private String demoLink;

    @NotNull
    private Long ownerId;
}
