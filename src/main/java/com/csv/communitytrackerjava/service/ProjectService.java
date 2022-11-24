package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectValidationDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;

public interface ProjectService {
    ProjectResponseDTO saveProject(ProjectValidationDTO projectValidationDTO);
    ProjectResponseDTO updateProject(Project project, int id) throws RecordNotFoundException;
    ProjectResponseDTO findAllProject();
}
