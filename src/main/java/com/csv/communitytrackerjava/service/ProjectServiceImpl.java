package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectRepository projectRepository;
    
    @Autowired
    ProjectMapper projectMapper;

    

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project, int id) throws RecordNotFoundException {

        Project projectFound = projectRepository.findById(id).orElseThrow();
        String newDesc = CaseUtils.toCamelCase(project.getProjectDesc(), true, ' ');
        projectFound.setProjectDesc(newDesc);
        Optional<Project> mapCode = Optional.ofNullable(projectRepository.findByProjectCode(project.getProjectCode()));
        if(mapCode.isEmpty()) {
            projectFound.setProjectCode(project.getProjectCode());
        }
        else{
                throw new EntityExistsException("Code already existing");
            }

        return projectFound;

    }

    @Override
    public Iterable<ProjectDTO> findAllProject() {
        return projectMapper.toListDTO(projectRepository.findAll());
    }

}
