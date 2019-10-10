package com.kaustubh.todo.model.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String errorCode;
}
