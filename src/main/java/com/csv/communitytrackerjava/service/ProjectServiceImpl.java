package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.*;
import com.csv.communitytrackerjava.exception.ProjectCodeExistException;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.People;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.PeopleRepository;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import org.apache.commons.text.CaseUtils;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectMapper projectMapper;
    
    @Autowired
    ModelMapper modelMapper;
   

    @Override
    public ProjectResponseDTO saveProject(ProjectAddDTO projectAddDTO) throws ProjectCodeExistException {
        ProjectPayloadDTO payloadDTO = new ProjectPayloadDTO();
        
        String projectCode = projectAddDTO.getProjectCode();
        Optional<Project> mapCode = projectRepository.findByProjectCode(projectCode);
        if (mapCode.isPresent()) {
            throw new ProjectCodeExistException("Project code already exist.");
        }
        String projectDesc = projectAddDTO.getProjectDesc();

        projectAddDTO.setProjectDesc(CaseUtils.toCamelCase(projectDesc, true, ' '));
        Project project = projectMapper.validationToModel(projectAddDTO);
        projectRepository.save(project);
        payloadDTO.setAdditionalProperty("project", projectMapper.toDTO(project));
        
        return toProjectResponseDTO("Successfully add project.", payloadDTO);
    }

    @Override
    public ProjectResponseDTO updateProject(ProjectUpdateDTO projectUpdateDTO, Integer id) throws Exception {
        ProjectPayloadDTO payloadDTO = new ProjectPayloadDTO();
        
        Project projectFound = projectRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found."));
        Optional<Project> projectCodeCheck = projectRepository.findByProjectCode(projectUpdateDTO.getProjectCode());
        String newDesc = projectUpdateDTO.getProjectDesc().isBlank() ? projectFound.getProjectDesc() : CaseUtils.toCamelCase(projectUpdateDTO.getProjectDesc(), true, ' ');
        projectUpdateDTO.setProjectDesc(newDesc);

        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setSkipNullEnabled(true);

        if(projectCodeCheck.isPresent()) {
            throw new ProjectCodeExistException("Project code already exist.");
        }

        projectFound.setProjectCode(projectUpdateDTO.getProjectCode());
        modelMapper.map(projectUpdateDTO, projectFound);
        payloadDTO.setAdditionalProperty("projects", projectMapper.toDTO(projectRepository.save(projectFound)));
        return toProjectResponseDTO("Successfully update project.", payloadDTO);
    }

    @Override
    public ProjectResponseDTO findAllProject() {
        ProjectPayloadDTO payloadDTO = new ProjectPayloadDTO();
        payloadDTO.setAdditionalProperty("projects", projectRepository.findAll());
        return toProjectResponseDTO("Successfully fetch all projects.", payloadDTO);
    }

    @Override
    public PageImpl<ProjectGetPeopleDTO> findPeopleByProjectId(Pageable pageable, Integer id) throws Exception{
            List<ProjectGetPeopleDTO> projectList = (
                    projectRepository.findByProjectId(id)
                            .stream()
                            .map(projects -> projectMapper.toGetPeopleDTO(projects))
                            .collect(Collectors.toList()));
            
            if(projectList.isEmpty()){
                throw new RecordNotFoundException("Project doesn't exist");
            }
            
            return new PageImpl<>(projectList, pageable, projectList.size());
    }


    public ProjectResponseDTO toProjectResponseDTO(String message, ProjectPayloadDTO payloadDTO){
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setMessage(message);
        projectResponseDTO.setPayload(payloadDTO);
        return projectResponseDTO;
    }


}
