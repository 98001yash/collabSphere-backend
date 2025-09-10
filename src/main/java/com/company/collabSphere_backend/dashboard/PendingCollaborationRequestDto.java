package com.company.collabSphere_backend.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
