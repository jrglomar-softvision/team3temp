package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectAddDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.exception.ProjectCodeExistException;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProjectService {
    ProjectResponseDTO saveProject(ProjectAddDTO projectAddDTO) throws ProjectCodeExistException;

    ProjectResponseDTO updateProject(ProjectUpdateDTO projectUpdateDTO, Integer id) throws Exception;

    ProjectResponseDTO findAllProject();
    
    Page<List<Project>> findPeopleByProjectId(Pageable pageable, Integer id) throws Exception;
    
    Page<List<Project>> findByProjectDesc(Pageable pageable, String projectDesc) throws Exception;
}
