package com.company.collabSphere_backend.service;


import com.company.collabSphere_backend.dtos.EndorsementRequestDto;
import com.company.collabSphere_backend.dtos.EndorsementResponseDto;
import com.company.collabSphere_backend.entity.Endorsement;
import com.company.collabSphere_backend.entity.Project;
import com.company.collabSphere_backend.entity.User;
import com.company.collabSphere_backend.enums.EndorsementStatus;
import com.company.collabSphere_backend.exceptions.ResourceNotFoundException;
import com.company.collabSphere_backend.repository.EndorsementRepository;
import com.company.collabSphere_backend.repository.ProjectRepository;
import com.company.collabSphere_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EndorsementService {

    private final EndorsementRepository endorsementRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public EndorsementResponseDto endorseProject(EndorsementRequestDto requestDto){
        log.info("Faculty {} is endorsing project {} ",requestDto.getFacultyId(), requestDto.getProjectId());

        User faculty = userRepository.findById(requestDto.getFacultyId())
                .orElseThrow(()->new ResourceNotFoundException("Faculty not found with id: "+requestDto.getFacultyId()));

        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + requestDto.getProjectId()));

        Endorsement endorsement = Endorsement.builder()
                .faculty(faculty)
                .project(project)
                .feedback(requestDto.getFeedback())
                .status(EndorsementStatus.ENDORSED)
                .build();

        Endorsement saved = endorsementRepository.save(endorsement);
        return modelMapper.map(saved, EndorsementResponseDto.class);
    }

    // Get endorsement by project
    public List<EndorsementResponseDto> getEndorsementsByProject(Long projectId) {
        log.info("Fetching endorsements for project {}", projectId);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + projectId));

        return endorsementRepository.findByProject(project)
                .stream()
                .map(e -> modelMapper.map(e, EndorsementResponseDto.class))
                .collect(Collectors.toList());
    }

    // get endorsement by faculty
    public List<EndorsementResponseDto> getEndorsementByFaculty(Long facultyId){
        log.info("Fetching endorsements given by faculty {}", facultyId);

        User faculty = userRepository.findById(facultyId)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id " + facultyId));

        return endorsementRepository.findByFaculty(faculty)
                .stream()
                .map(e -> modelMapper.map(e, EndorsementResponseDto.class))
                .collect(Collectors.toList());
    }

    // revoke endorsement
    public EndorsementResponseDto revokeEndorsement(Long endorsementId){
        log.info("Revoking endorsement with id: {}",endorsementId);

        Endorsement endorsement =endorsementRepository.findById(endorsementId)
                .orElseThrow(()->new ResourceNotFoundException("Endorsement not found with id "+endorsementId));

        endorsement.setStatus(EndorsementStatus.REVOKED);
        Endorsement updated = endorsementRepository.save(endorsement);

        return modelMapper.map(updated, EndorsementResponseDto.class);
    }
}
