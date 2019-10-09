package com.kaustubh.todo.exception;

import lombok.Getter;

@Getter
public class NoDataFoundException extends Exception {
    public NoDataFoundException(String message) {
        super(message);
    }
}
