package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ApiErrorDTO;
import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
    
    ProjectPayloadDTO projectPayloadDTO = new ProjectPayloadDTO();
    
    @Override
    public ProjectResponseDTO formatBadRequest(Exception exception) {

        ApiErrorDTO apiError = new ApiErrorDTO(BAD_REQUEST);
        apiError.setType(exception.getClass().getTypeName());
        projectResponseDTO.setErrors(List.of(apiError));
        projectResponseDTO.setMessage(exception.getMessage());
        projectPayloadDTO.setAdditionalProperty("projects", Collections.emptyList());
        projectResponseDTO.setPayload(projectPayloadDTO);

        return projectResponseDTO;
    }
}
