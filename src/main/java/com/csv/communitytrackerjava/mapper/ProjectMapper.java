package com.csv.communitytrackerjava.mapper;

import com.csv.communitytrackerjava.dto.ProjectDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectValidationDTO;
import com.csv.communitytrackerjava.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO(Project model);

    Project toModel(ProjectDTO dto);
    
    Iterable<ProjectDTO> toListDTO(Iterable<Project> listModel);

    Iterable<Project> toListModel(Iterable<ProjectDTO> listDto);

    Project validationToModel(ProjectValidationDTO projectValidationDTO);
    

}