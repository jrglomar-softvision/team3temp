package com.csv.communitytrackerjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnexpectedCharactersException extends Exception {
    public UnexpectedCharactersException(String message){
        super(message);
    }
}
