package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project, int id) throws RecordNotFoundException {
        Project projectFound = projectRepository.findById(id).orElseThrow();
            String newDesc = CaseUtils.toCamelCase(project.getProjectDesc(), true, ' ');
            projectFound.setProjectDesc(newDesc);
        return projectFound;
    }
//    String newDesc = CaseUtils.toCamelCase(project.getProjectDesc(), true, ' ');
//        char newDesc = Character.toUpperCase(project.getProjectDesc().charAt(0));
//        char toPascal = Character.toUpperCase(newDesc.charAt(0));
//        return projectRepository.findById(id).map(newProj ->{
//            newProj.setProjectDesc(newDesc);
//            return projectRepository.save(newProj);
//        });
}
