package com.kaustubh.todo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends Exception {
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public ApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApiException(String message) {
        super(message);
    }
}
