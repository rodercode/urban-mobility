package com.example.urbanmobility.handler;
import com.example.urbanmobility.entity.ErrorRes;
import com.example.urbanmobility.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiRequestExceptionHandler {

   @ExceptionHandler
    public ResponseEntity<ErrorRes> handleException(ResourceNotFoundException e){
        ErrorRes error = new ErrorRes(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}