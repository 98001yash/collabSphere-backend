package com.company.collabSphere_backend.service;


import com.company.collabSphere_backend.dtos.OpportunityRequestDto;
import com.company.collabSphere_backend.dtos.OpportunityResponseDto;
import com.company.collabSphere_backend.entity.Opportunity;
import com.company.collabSphere_backend.enums.OpportunityStatus;
import com.company.collabSphere_backend.exceptions.ResourceNotFoundException;
import com.company.collabSphere_backend.repository.OpportunityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public OpportunityResponseDto createOpportunity(OpportunityRequestDto requestDto){
        log.info("Creating new opportunity: {}",requestDto.getTitle());

        Opportunity opportunity = modelMapper.map(requestDto, Opportunity.class);
        opportunity.setStatus(OpportunityStatus.DRAFT);

        Opportunity saved = opportunityRepository.save(opportunity);
        return modelMapper.map(saved, OpportunityResponseDto.class);
    }

    @Transactional
    public OpportunityResponseDto publishOpportunity(Long id){
        log.info("Publishing opportunity with id: {}",id);
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found with id " + id));

        opportunity.setStatus(OpportunityStatus.PUBLISHED);
        return modelMapper.map(opportunityRepository.save(opportunity), OpportunityResponseDto.class);
    }

    public List<OpportunityResponseDto> getActiveOpportunities() {
        log.info("Fetching all active opportunities");

        return opportunityRepository.findByStatusAndApplicationDeadlineAfter(
                        OpportunityStatus.PUBLISHED,
                        LocalDateTime.now()
                )
                .stream()
                .map(op -> modelMapper.map(op, OpportunityResponseDto.class))
                .collect(Collectors.toList());
    }

    public OpportunityResponseDto getOpportunityById(Long id) {
        log.info("Fetching opportunity with id {}", id);

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found with id " + id));

        return modelMapper.map(opportunity, OpportunityResponseDto.class);
    }

    public void deleteOpportunity(Long id) {
        log.info("Deleting opportunity with id {}", id);

        if (!opportunityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Opportunity not found with id " + id);
        }
        opportunityRepository.deleteById(id);
    }

}
