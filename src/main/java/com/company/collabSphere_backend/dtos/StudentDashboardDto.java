package com.company.collabSphere_backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDashboardDto {

    private Long studentId;
    private String name;
    private String email;

    private List<OpportunityDto> activeOpportunities;
    private List<CollaborationRequestDashboardDto> collaborationRequests;
    private List<EndorsementDto> endorsementReceived;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class OpportunityDto {
    private Long id;
    private String title;
    private String type;
    private String mode;
    private String organization;
    private BigDecimal stipendMin;
    private BigDecimal stipendMax;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime applicationDeadline;
    private Double latitude;
    private Double longitude;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class CollaborationRequestDashboardDto {
    private Long requestId;
    private Long projectId;
    private String projectTitle;
    private String status;
    private LocalDateTime appliedAt;
    private String ownerName;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class EndorsementDto {
    private Long endorsementId;
    private String from;
    private String project;
    private String message;
    private LocalDateTime date;
}

