package com.academy.communitytrackerjava.service;

import com.academy.communitytrackerjava.exception.IsActiveNotZeroOrOneException;
import com.academy.communitytrackerjava.model.Project;
import com.academy.communitytrackerjava.model.ProjectDTO;
import com.academy.communitytrackerjava.repository.ProjectRepository;
import org.apache.commons.text.CaseUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ProjectDTO saveProject(ProjectDTO projectDTO) throws IsActiveNotZeroOrOneException {
        Integer isActive = projectDTO.getIsActive();
        if (isActive != 0 && isActive != 1)
            throw new IsActiveNotZeroOrOneException();

        String projectDesc = projectDTO.getProjectDesc();
        projectDTO.setProjectDesc(CaseUtils.toCamelCase(projectDesc, true, ' '));

        Project project = convertDto(projectDTO);
        projectRepository.save(project);
        projectDTO.setProjectId(project.getProjectId());
        return projectDTO;
    }

    private Project convertDto(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        if (projectDTO.getIsActive() == 0)
            project.setIsActive(Boolean.FALSE);
        else if (projectDTO.getIsActive() == 1)
            project.setIsActive(Boolean.TRUE);
        return project;
    }

}
