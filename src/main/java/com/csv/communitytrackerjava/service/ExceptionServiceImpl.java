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

    ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();

    ProjectPayloadDTO projectPayloadDTO = new ProjectPayloadDTO();

    @Override
    public ProjectResponseDTO formatBadRequest(Exception exception) {

        setProjectResponseDTO(exception);
        projectResponseDTO.setMessage(exception.getMessage());
        return projectResponseDTO;
    }

    @Override
    public ProjectResponseDTO formatBadRequest(Exception exception, String message) {
        setProjectResponseDTO(exception);
        projectResponseDTO.setMessage(message);
        return projectResponseDTO;
    }

    private ProjectResponseDTO setProjectResponseDTO(Exception exception) {
        ApiErrorDTO apiError = new ApiErrorDTO(BAD_REQUEST);
        apiError.setType(exception.getClass().getTypeName());
        projectPayloadDTO.setAdditionalProperty("projects", Collections.emptyList());
        return toProjectResponseDTO(apiError, exception.getMessage(), projectPayloadDTO);
    }


    public ProjectResponseDTO toProjectResponseDTO(ApiErrorDTO apiError, String message, ProjectPayloadDTO projectPayloadDTO){
        projectResponseDTO.setErrors(List.of(apiError));
        projectResponseDTO.setMessage(message);
        projectResponseDTO.setPayload(projectPayloadDTO);
        return projectResponseDTO;

    }

}
