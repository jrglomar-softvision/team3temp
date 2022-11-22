package com.csv.communitytrackerjava.service;


import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Override
    public Iterable<Project> findAllProject() {
        return null;
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
