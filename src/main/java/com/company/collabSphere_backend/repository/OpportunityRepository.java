package com.company.collabSphere_backend.repository;

import com.company.collabSphere_backend.entity.Opportunity;
import com.company.collabSphere_backend.enums.OpportunityStatus;
import com.company.collabSphere_backend.enums.OpportunityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

    List<Opportunity> findByStatus(Opportunity status);

    List<Opportunity> findByTypeAndStatus(OpportunityType type, OpportunityStatus status);

    List<Opportunity> findByStatusAndApplicationDeadlineAfter(OpportunityStatus status, LocalDateTime now);
}
