package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ApiErrorDTO;
import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ExceptionServiceImpl implements ExceptionService {


    @Override
    public ProjectResponseDTO formatBadRequest(Exception exception) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        setProjectResponseDTO(exception);
        projectResponseDTO.setMessage(exception.getMessage());
        return projectResponseDTO;
    }

    @Override
    public ProjectResponseDTO formatBadRequest(Exception exception, String message) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        setProjectResponseDTO(exception);
        projectResponseDTO.setMessage(message);
        return projectResponseDTO;
    }

    private ProjectResponseDTO setProjectResponseDTO(Exception exception) {
        ApiErrorDTO apiError = new ApiErrorDTO(BAD_REQUEST);
        apiError.setType(exception.getClass().getName());
        return toProjectResponseDTO(apiError, exception.getMessage());
    }

    public ProjectResponseDTO toProjectResponseDTO(ApiErrorDTO apiError, String message){
        // Payload as empty
        ProjectPayloadDTO projectPayloadDTO = new ProjectPayloadDTO();
        projectPayloadDTO.setAdditionalProperty("projects", Collections.emptyList());

        // Settings response
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setErrors(List.of(apiError));
        projectResponseDTO.setMessage(message);
        projectResponseDTO.setPayload(projectPayloadDTO);
        return projectResponseDTO;
    }

}
