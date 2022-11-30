package com.csv.communitytrackerjava.mapper;

import com.csv.communitytrackerjava.dto.*;
import com.csv.communitytrackerjava.model.Project;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO(Project model);

    Project toModel(ProjectDTO dto);

    Iterable<ProjectDTO> toListDTO(Iterable<Project> listModel);

    Iterable<Project> toListModel(Iterable<ProjectDTO> listDto);

    Project validationToModel(ProjectAddDTO projectAddDTO);

    ProjectResponseDTO toResponseDTO(ProjectUpdateDTO projectUpdateDTO);
    
    ProjectGetPeopleDTO toGetPeopleDTO(Project getPeopleDTO);
}