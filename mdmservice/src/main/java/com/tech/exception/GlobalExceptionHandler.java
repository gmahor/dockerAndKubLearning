package com.tech.exception;

import com.tech.commons.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseHandler responseHandler;

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<Object> invalidRoleException(InvalidRoleException e, WebRequest webRequest) {
        return responseHandler.errorResp(webRequest.getDescription(false),
                e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

}
