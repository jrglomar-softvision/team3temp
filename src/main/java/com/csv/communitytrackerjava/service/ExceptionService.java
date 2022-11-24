package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.model.Project;

public interface ExceptionService {
    ProjectResponseDTO formatErrorResponse (Exception exception, String customMessage);
}
