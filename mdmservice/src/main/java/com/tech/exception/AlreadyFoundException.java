package com.tech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyFoundException extends RuntimeException{
    public AlreadyFoundException(String resourceName,String fieldName,String fieldValue) {
        super(String.format("%s already present with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
