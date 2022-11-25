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

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
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

    @Autowired
    ModelMapper modelMapper;

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
    public ProjectResponseDTO updateProject(ProjectUpdateDTO projectUpdateDTO, int id) throws Exception {
        Project projectFound = projectRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found."));
        Project projectCodeCheck = projectRepository.findByProjectCode(projectUpdateDTO.getProjectCode()).orElseThrow(() -> new ProjectCodeExistException("Project code already exist."));
        String projectDesc = projectUpdateDTO.getProjectDesc();
        String newDesc = projectDesc == null || projectDesc.isEmpty() ? projectFound.getProjectDesc() : CaseUtils.toCamelCase(projectUpdateDTO.getProjectDesc(), true, ' ');
        projectUpdateDTO.setProjectDesc(newDesc);

        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setSkipNullEnabled(true);


        modelMapper.map(projectUpdateDTO, projectFound);
        payloadDTO.setAdditionalProperty("projects", projectMapper.toDTO(projectRepository.save(projectFound)));
        return toProjectResponseDTO("Successfully update project.", payloadDTO);
    }

    @Override
    public ProjectResponseDTO findAllProject() {

        payloadDTO.setAdditionalProperty("projects", projectMapper.toListDTO(projectRepository.findAll()));
        return toProjectResponseDTO("Successfully fetch all projects.", payloadDTO);

    }

    @Override
    public void deleteProject(int id) throws RecordNotFoundException {
        Project projectFound = projectRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Project to delete is not found."));
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setSkipNullEnabled(false);
        Project project = new Project();
        modelMapper.map(project, projectFound);
        projectFound.setIsActive(false);
        projectRepository.save(projectFound);
    }

    public ProjectResponseDTO toProjectResponseDTO(String message, ProjectPayloadDTO payloadDTO) {
        projectResponseDTO.setMessage(message);
        projectResponseDTO.setPayload(payloadDTO);

        return projectResponseDTO;
    }

}
