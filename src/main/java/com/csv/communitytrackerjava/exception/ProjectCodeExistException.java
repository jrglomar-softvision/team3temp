package com.csv.communitytrackerjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProjectCodeExistException extends Exception {
    public ProjectCodeExistException(String message) {
        super(message);
    }
}
