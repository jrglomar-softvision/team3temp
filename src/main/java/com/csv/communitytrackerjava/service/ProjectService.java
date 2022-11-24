package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;

import java.util.Optional;

public interface ProjectService {
    Project saveProject(Project project);
    Project updateProject(Project project, int id) throws RecordNotFoundException;
    Iterable<ProjectDTO> findAllProject();
}
