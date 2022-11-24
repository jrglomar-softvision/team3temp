package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.service.ExceptionService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice
public class ExceptionController {


    @Autowired
    ExceptionService exceptionService;

    ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();

    ProjectPayloadDTO payloadDTO = new ProjectPayloadDTO();
    
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ProjectResponseDTO> handleNoSuchElementException(NoSuchElementException e){
        return new ResponseEntity<>(exceptionService.formatErrorResponse(e, "Record Not Found"), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ProjectResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        return new ResponseEntity<>(exceptionService.formatErrorResponse(e, "Unexpected Characters"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String field = Objects.requireNonNull(e.getFieldError()).getField();
        String defaultMessage = e.getFieldError().getDefaultMessage();
        String code = e.getFieldError().getCode();
        assert code != null;
        String message = switch (code) {
            case "Size" -> "Size of " + field + " must not be more than 100";
            case "NotNull" -> field + " is required";
            default -> "MethodArgumentNotValidException caught";
        };

        setResponse(field, defaultMessage, message);
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PSQLException.class)
    public ResponseEntity<Object> handlePSQL(PSQLException e) {
        String defaultMessage = e.getServerErrorMessage().getMessage();
        String message = "PSQLException caught";
        if (e.getServerErrorMessage().getSQLState().equals("23505"))
            message = "projectCode should be unique";

        setResponse("projectCode", defaultMessage, message);
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormat(InvalidFormatException e) {
        String field = e.getPath().get(0).toString();
        String message = "Invalid data format on " + field;

        setResponse(field, e.getOriginalMessage(), message);
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.BAD_REQUEST);
    }

    private void setResponse(String field, String defaultMessage, String message) {
        projectResponseDTO.setErrors(List.of(new ObjectError(field, defaultMessage)));
        projectResponseDTO.setMessage(message);
        payloadDTO.setAdditionalProperty("projects", null);
        projectResponseDTO.setPayload(payloadDTO);
    }

}
