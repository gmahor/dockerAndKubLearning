package com.tech.exception;

import com.tech.commons.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseHandler responseHandler;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        return responseHandler.errorResp(webRequest.getDescription(false),
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<Object> invalidRoleException(InvalidRoleException e, WebRequest webRequest) {
        return responseHandler.errorResp(webRequest.getDescription(false),
                e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                  WebRequest webRequest) {
        return responseHandler.errorResp(webRequest.getDescription(false),
                exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }

}
