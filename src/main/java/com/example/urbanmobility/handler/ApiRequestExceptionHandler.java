package com.example.urbanmobility.handler;

import com.example.urbanmobility.exception.ApiRequestException;
import org.springframework.http.ResponseEntity;

public class ApiRequestExceptionHandler {
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        // 1. Create payload containing exception details
        // 2. Return response entity

    }
}
