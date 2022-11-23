package com.academy.communitytrackerjava.controller;

import com.academy.communitytrackerjava.exception.IsActiveNotZeroOrOneException;
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
            default -> "MethodArgumentNotValidException caught";
        };

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("errors", e.getFieldError());
        response.put("message", message);
        response.put("payload", "");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PSQLException.class)
    public ResponseEntity<Object> handlePSQL(PSQLException e) {
        String message = "PSQLException caught";
        if (e.getServerErrorMessage().getSQLState().equals("23505"))
            message = "projectCode should be unique";

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("errors", e.getServerErrorMessage());
        response.put("message", message);
        response.put("payload", "");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IsActiveNotZeroOrOneException.class)
    public ResponseEntity<Object> handleIsActiveNotZeroOrOne(IsActiveNotZeroOrOneException e) {
        String message = "isActive should only be 0 or 1";
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("errors", e.getMessage());
        response.put("message", message);
        response.put("payload", "");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
