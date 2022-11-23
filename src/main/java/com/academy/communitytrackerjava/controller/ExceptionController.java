package com.academy.communitytrackerjava.controller;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String code = Objects.requireNonNull(e.getFieldError()).getCode();
        String field = e.getFieldError().getField();
        assert code != null;
        String message = switch (code) {
            case "Size" -> "Size of " + field + " must not be more than 100";
            case "NotNull" -> field + " is required";
            case "Max", "Min" -> "isActive should only be 0 or 1";
            default -> "MethodArgumentNotValidException caught";
        };
        Map<String, Object> response = returnResponse(e.getFieldError(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PSQLException.class)
    public ResponseEntity<Object> handlePSQL(PSQLException e) {
        String message = "PSQLException caught";
        if (e.getServerErrorMessage().getSQLState().equals("23505"))
            message = "projectCode should be unique";
        Map<String, Object> response = returnResponse(e.getServerErrorMessage(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> returnResponse(Object errors, String message) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("errors", errors);
        response.put("message", message);
        response.put("payload", null);
        return response;
    }

}
