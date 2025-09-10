package com.company.collabSphere_backend.repository;

import com.company.collabSphere_backend.entity.Opportunity;
import com.company.collabSphere_backend.entity.User;
import com.company.collabSphere_backend.enums.OpportunityStatus;
import com.company.collabSphere_backend.enums.OpportunityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

    List<Opportunity> findByStatus(OpportunityStatus status);

    List<Opportunity> findByTypeAndStatus(OpportunityType type, OpportunityStatus status);


    // for student dashboard: get all active dashboard + deadline not passed
    List<Opportunity> findByStatusAndApplicationDeadlineAfter(OpportunityStatus status,
                                                              LocalDateTime now);


    // for faculty dashboard: get all opportunity created by a faculty
    List<Opportunity> findByCreatedById(Long facultyId);

    List<Opportunity> findByOwner(User faculty);

}
