package com.kaustubh.todo.controller;

import com.kaustubh.todo.exception.ApiException;
import com.kaustubh.todo.exception.NoSuchResourceException;
import com.kaustubh.todo.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleException(ApiException e) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(e.getMessage());
        response.setErrorCode("E005");

        return ResponseEntity.status(e.getHttpStatus()).body(response);
    }

    @ExceptionHandler(NoSuchResourceException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchResourceException e) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(e.getMessage());
        response.setErrorCode("E004");

        return ResponseEntity.status(e.getHttpStatus()).body(response);
    }

}
