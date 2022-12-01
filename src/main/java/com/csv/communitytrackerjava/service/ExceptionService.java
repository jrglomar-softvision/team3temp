package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectResponseDTO;

public interface ExceptionService {
    ProjectResponseDTO formatBadRequest(Exception exception);
    ProjectResponseDTO formatBadRequest(Exception exception, String message);
}
