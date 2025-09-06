package com.company.collabSphere_backend.dtos;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponseDto {

    private Long id;
    private String title;
    private String description;
    private String githubLink;
    private String demoLink;

    private Long ownerId;
    private String ownerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
