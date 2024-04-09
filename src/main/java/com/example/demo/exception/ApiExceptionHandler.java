package com.example.demo.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiResponseException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, badRequest);
    }
/*
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Map<String, String>> handleBusinessException(NoSuchElementException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error Message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
        }*/
    

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Map<String, String>> handleBusinessException(NoSuchElementException ex) {
        
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "id not found ");
        errorMap.put("message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
        }
   
    
    
    
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    public Map<String,String> handleInvalidValidArgument(ConstraintViolationException ex){
    	Map<String,String> errorMap=new HashMap<>();
        errorMap.put("error", "valid input should be eneterd ");

        errorMap.put("error Message", ex.getMessage());
    	
        return errorMap;
    	
    	
    }
}

