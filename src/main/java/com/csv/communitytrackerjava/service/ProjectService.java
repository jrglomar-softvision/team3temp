package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;

public interface ProjectService {
    
    Iterable<Project> findAllProject();
    
    Project findProjectById(Integer id) throws RecordNotFoundException;
    
    Project saveProject(Project project);
    
    Project updateProject(Project project, Integer id) throws RecordNotFoundException;
    
    Project deleteProject(Integer id) throws RecordNotFoundException;
    
}
