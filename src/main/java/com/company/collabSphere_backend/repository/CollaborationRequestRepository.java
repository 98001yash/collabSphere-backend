package com.company.collabSphere_backend.repository;

import com.company.collabSphere_backend.entity.CollaborationRequest;
import com.company.collabSphere_backend.entity.Project;
import com.company.collabSphere_backend.entity.User;
import com.company.collabSphere_backend.enums.CollaborationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest,Long> {

    boolean existsByProjectAndStudent(Project project, User student);

    Optional<CollaborationRequest> findByStudentAndProject(User student, Project project);

    List<CollaborationRequest> findByProject(Project project);

    List<CollaborationRequest> findByStudent(User student);
    List<CollaborationRequest> findByProjectAndStatus(Project project, CollaborationStatus statue);

}
