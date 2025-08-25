package com.tech.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseHandler {

    private ResponseHandler() {
    }

    public ResponseEntity<Object> successResp(Object response, String message, boolean isSuccess, HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", response);
        map.put("message", message);
        map.put("isSuccess", isSuccess);
        map.put("statusCode", httpStatus.value());
        map.put("timeStamp", LocalDateTime.now());
        return new ResponseEntity<>(map, httpStatus);
    }


    public ResponseEntity<Object> errorResp(String apiPath,  String errorMessage,HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("apiPath", apiPath);
        map.put("errorMessage", errorMessage);
        map.put("errorTime", LocalDateTime.now());
        return new ResponseEntity<>(map, httpStatus);
    }
}
