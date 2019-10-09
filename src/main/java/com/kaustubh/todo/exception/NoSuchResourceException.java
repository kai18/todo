package com.kaustubh.todo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoSuchResourceException extends Exception {
    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NoSuchResourceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public NoSuchResourceException(String message) {
        super(message);
    }
}
