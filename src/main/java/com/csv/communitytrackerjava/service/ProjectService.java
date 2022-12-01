package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectAddDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.exception.ProjectCodeExistException;

public interface ProjectService {
    ProjectResponseDTO saveProject(ProjectAddDTO projectAddDTO) throws ProjectCodeExistException;

    ProjectResponseDTO updateProject(ProjectUpdateDTO projectUpdateDTO, int id) throws Exception;

    ProjectResponseDTO findAllProject();

    ProjectResponseDTO deleteProject(int id) throws Exception;

    ProjectResponseDTO findProjectById(int id) throws Exception;

}
