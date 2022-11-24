package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectAddDTO;
import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.exception.ProjectCodeExistException;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ProjectResponseDTO saveProject(ProjectAddDTO projectAddDTO) throws ProjectCodeExistException {
        String projectCode = projectAddDTO.getProjectCode();
        Optional<Project> mapCode = Optional.ofNullable(projectRepository.findByProjectCode(projectCode));
        if (mapCode.isPresent()) {
            throw new ProjectCodeExistException("Project code already exist.");
        }
        String projectDesc = projectAddDTO.getProjectDesc();
        projectAddDTO.setProjectDesc(CaseUtils.toCamelCase(projectDesc, true, ' '));
        Project project = projectMapper.validationToModel(projectAddDTO);
        projectRepository.save(project);
        setProjectResponseDTO("Successfully add project.", projectMapper.toDTO(project));

        return projectResponseDTO;
    }

    @Override
    public ProjectResponseDTO updateProject(ProjectUpdateDTO projectUpdateDTO, int id) throws Exception {
        Project projectFound = projectRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found."));
        String projectDesc = projectUpdateDTO.getProjectDesc();
        String newDesc = projectDesc == null || projectDesc.isEmpty()
                ? projectFound.getProjectDesc()
                : CaseUtils.toCamelCase(projectUpdateDTO.getProjectDesc(), true, ' ');
        projectFound.setProjectDesc(newDesc);
        Optional<Project> mapCode = Optional.ofNullable(projectRepository.findByProjectCode(projectUpdateDTO.getProjectCode()));
        if (mapCode.isEmpty()) {
            String projectCode = projectUpdateDTO.getProjectCode();
            projectFound.setProjectCode(projectCode == null || projectCode.isEmpty()
                    ? projectFound.getProjectCode()
                    : projectUpdateDTO.getProjectCode());
        } else {
            throw new ProjectCodeExistException("Project code already exist.");
        }
        projectRepository.save(projectFound);
        setProjectResponseDTO("Successfully update project.", projectMapper.toDTO(projectFound));

        return projectResponseDTO;
    }

    @Override
    public ProjectResponseDTO findAllProject() {

        projectResponseDTO.setMessage("Successfully fetch all projects.");
        payloadDTO.setAdditionalProperty("projects", projectMapper.toListDTO(projectRepository.findAll()));
        projectResponseDTO.setPayload(payloadDTO);

        return projectResponseDTO;
    }

    private void setProjectResponseDTO(String message, Object payload) {
        projectResponseDTO.setMessage(message);
        payloadDTO.setAdditionalProperty("projects", payload);
        projectResponseDTO.setPayload(payloadDTO);
    }

}
