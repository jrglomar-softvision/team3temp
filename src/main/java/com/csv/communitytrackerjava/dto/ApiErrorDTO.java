package com.csv.communitytrackerjava.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiErrorDTO {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    
    private String type;

    public ApiErrorDTO() {
        timestamp = LocalDateTime.now();
    }

    public ApiErrorDTO(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiErrorDTO(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.type = "Unexpected error";
    }

    ApiErrorDTO(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.type = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}