package com.csv.communitytrackerjava.service;


import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Iterable<Project> findAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Project findProjectById(Integer id) throws RecordNotFoundException {
        return null;
    }

    @Override
    public Project saveProject(Project project) {
        return null;
    }

    @Override
    public Project updateProject(Project project, Integer id) throws RecordNotFoundException {
        return null;
    }

    @Override
    public Project deleteProject(Integer id) throws RecordNotFoundException {
        return null;
    }
}
