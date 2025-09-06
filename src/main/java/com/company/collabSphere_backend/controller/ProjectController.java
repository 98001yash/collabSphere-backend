package com.company.collabSphere_backend.controller;

import com.company.collabSphere_backend.dtos.ProjectRequestDto;
import com.company.collabSphere_backend.dtos.ProjectResponseDto;
import com.company.collabSphere_backend.enums.ProjectStatus;
import com.company.collabSphere_backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // Create project
    @PostMapping("/create/{ownerId}")
    public ResponseEntity<ProjectResponseDto> createProject(
            @PathVariable Long ownerId,
            @Valid @RequestBody ProjectRequestDto projectRequestDto
    ) {
        log.info("Creating project for ownerId: {}", ownerId);
        return ResponseEntity.ok(projectService.createProject(projectRequestDto, ownerId));
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        log.info("Fetching all projects");
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // Get project by id
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        log.info("Fetching project with id: {}", id);
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    // Update project status
    @PatchMapping("/{id}/status")
    public ResponseEntity<ProjectResponseDto> updateProjectStatus(
            @PathVariable Long id,
            @RequestParam ProjectStatus status
    ) {
        log.info("Updating status for project id: {}", id);
        return ResponseEntity.ok(projectService.updateProjectStatus(id, status));
    }

    // Delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        log.info("Deleting project with id: {}", id);
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
