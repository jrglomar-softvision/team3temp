package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
    
    

    @Override
    public ProjectResponseDTO formatErrorResponse(Exception exception) {

        ObjectError objectError = new ObjectError(exception.getClass().getName(), exception.getMessage());
        
        projectResponseDTO.setErrors(List.of(objectError));
        projectResponseDTO.setMessage("Successfully update project.");

        return projectResponseDTO;
    }
}
