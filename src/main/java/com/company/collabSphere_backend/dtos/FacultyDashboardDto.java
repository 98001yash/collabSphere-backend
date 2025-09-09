package com.company.collabSphere_backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyDashboardDto {
    private Long facultyId;
    private String name;
    private String email;

    private List<ProjectDto> ownedProjects;
    private List<PendingCollaborationRequestDto> pendingCollaborationRequests;
    private List<EndorsementDto> endorsementsGiven;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ProjectDto {
    private Long projectId;
    private String title;
    private String description;
    private Integer collaboratorsCount;
    private String status; // ONGOING, COMPLETED, DRAFT
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class PendingCollaborationRequestDto {
    private Long requestId;
    private Long studentId;
    private String studentName;
    private Long projectId;
    private String projectTitle;
    private LocalDateTime appliedAt;
}
