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
    public ProjectResponseDTO formatErrorResponse(Exception exception, String customMessage) {

        ApiErrorDTO apiError = new ApiErrorDTO(BAD_REQUEST);
        apiError.setMessage(exception.getMessage());

        projectResponseDTO.setErrors(List.of(apiError));
        
        projectResponseDTO.setMessage(customMessage);
        projectPayloadDTO.setAdditionalProperty("projects", Collections.emptyList());
        projectResponseDTO.setPayload(projectPayloadDTO);

        return projectResponseDTO;
    }
}
