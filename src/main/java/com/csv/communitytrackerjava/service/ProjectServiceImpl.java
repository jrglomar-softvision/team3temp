package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectValidationDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import org.apache.commons.text.CaseUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import javax.persistence.EntityExistsException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    
    @Autowired
    ProjectMapper projectMapper;

    ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
    
    ProjectPayloadDTO payloadDTO = new ProjectPayloadDTO();
    
    @Override
    public ProjectResponseDTO saveProject(ProjectValidationDTO projectValidationDTO) {
        String projectDesc = projectValidationDTO.getProjectDesc();
        projectValidationDTO.setProjectDesc(CaseUtils.toCamelCase(projectDesc, true, ' '));

        Project project = projectMapper.validationToModel(projectValidationDTO);
        project = projectRepository.save(project);

        projectResponseDTO.setErrors(List.of(new ObjectError("test", "test")));
        projectResponseDTO.setMessage("Successfully add project.");
        payloadDTO.setAdditionalProperty("projects", projectMapper.toDTO(project));
        projectResponseDTO.setPayload(payloadDTO);

        return projectResponseDTO;
    }

    @Override
    public ProjectResponseDTO updateProject(Project project, int id) throws RecordNotFoundException {

        Project projectFound = projectRepository.findById(id).orElseThrow();
        String newDesc = project.getProjectDesc().isEmpty() ? projectFound.getProjectDesc() : CaseUtils.toCamelCase(project.getProjectDesc(), true, ' ');
        projectFound.setProjectDesc(newDesc);
        Optional<Project> mapCode = Optional.ofNullable(projectRepository.findByProjectCode(project.getProjectCode()));
        if (mapCode.isEmpty()) {
            projectFound.setProjectCode(project.getProjectCode() == null ? projectFound.getProjectCode() : project.getProjectCode());
        } else {
            throw new NoSuchElementException("Code already existing");
        }
        projectResponseDTO.setMessage("Successfully update project.");
        payloadDTO.setAdditionalProperty("projects", projectMapper.toDTO(projectFound));
        projectResponseDTO.setPayload(payloadDTO);

        return projectResponseDTO;
    }

    @Override
    public ProjectResponseDTO findAllProject() {

        projectResponseDTO.setMessage("Successfully fetch all projects.");
        payloadDTO.setAdditionalProperty("projects", projectMapper.toListDTO(projectRepository.findAll()));
        projectResponseDTO.setPayload(payloadDTO);

        return projectResponseDTO;
    }

}
