package com.company.collabSphere_backend.service;


import com.company.collabSphere_backend.dtos.ProjectRequestDto;
import com.company.collabSphere_backend.dtos.ProjectResponseDto;
import com.company.collabSphere_backend.entity.Project;
import com.company.collabSphere_backend.entity.User;
import com.company.collabSphere_backend.enums.ProjectStatus;
import com.company.collabSphere_backend.exceptions.ResourceNotFoundException;
import com.company.collabSphere_backend.repository.ProjectRepository;
import com.company.collabSphere_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto dto, Long ownerId){
        log.info("creating project with title: {}",dto.getTitle());

        User owner = userRepository.findById(ownerId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id: "+ownerId));

        Project project = modelMapper.map(dto, Project.class);
        project.setOwner(owner);
        project.setStatus(ProjectStatus.PENDING);

        Project savedProject = projectRepository.save(project);
                return modelMapper.map(savedProject, ProjectResponseDto.class);
    }

    public ProjectResponseDto getProjectById(Long projectId){
        log.debug("Fetching project with ifd: {}",projectId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()->new ResourceNotFoundException("Project not found with id: "+projectId));

        return modelMapper.map(project, ProjectResponseDto.class);
    }

    public List<ProjectResponseDto> getAllProjects(){
        log.debug("Fetching all projects");
        return projectRepository.findAll()
                .stream()
                .map(project->modelMapper.map(project, ProjectResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto dto) {
        log.info("Updating project with id: {}", projectId);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        modelMapper.map(dto, project); // update fields

        Project updated = projectRepository.save(project);
        return modelMapper.map(updated, ProjectResponseDto.class);
    }

    @Transactional
    public void deleteProject(Long projectId) {
        log.warn("Deleting project with id: {}", projectId);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        projectRepository.delete(project);
    }
}
