package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.dto.ProjectAddDTO;
import com.csv.communitytrackerjava.exception.ProjectCodeExistException;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import org.apache.commons.text.CaseUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

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
    public ProjectResponseDTO saveProject(ProjectAddDTO projectAddDTO) {
        String projectDesc = projectAddDTO.getProjectDesc();
        projectAddDTO.setProjectDesc(CaseUtils.toCamelCase(projectDesc, true, ' '));
        Project project = projectMapper.validationToModel(projectAddDTO);
        projectRepository.save(project);

        projectResponseDTO.setErrors(List.of(new ObjectError("test", "test")));
        projectResponseDTO.setMessage("Successfully add project.");
        payloadDTO.setAdditionalProperty("projects", projectMapper.toDTO(project));
        projectResponseDTO.setPayload(payloadDTO);

        return projectResponseDTO;
    }

    @Override
    public ProjectResponseDTO updateProject(ProjectUpdateDTO projectUpdateDTO, int id) throws Exception{
        Project projectFound = projectRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found."));
        String projectDesc = projectUpdateDTO.getProjectDesc();
        String newDesc = projectDesc == null || projectDesc.isEmpty() ? projectFound.getProjectDesc() : CaseUtils.toCamelCase(projectUpdateDTO.getProjectDesc(), true, ' ');
        projectFound.setProjectDesc(newDesc);
        Optional<Project> mapCode = Optional.ofNullable(projectRepository.findByProjectCode(projectUpdateDTO.getProjectCode()));
        if (mapCode.isEmpty()) {
            String projectCode = projectUpdateDTO.getProjectCode();
            projectFound.setProjectCode(projectCode == null || projectCode.isEmpty() ? projectFound.getProjectCode() : projectUpdateDTO.getProjectCode());
        } else {
            throw new ProjectCodeExistException("Project code already exist.");
        }
        projectRepository.save(projectFound);
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
