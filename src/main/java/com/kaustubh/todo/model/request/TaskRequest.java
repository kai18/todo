package com.kaustubh.todo.model.request;

import lombok.Data;

@Data
public class TaskRequest {
    private Integer taskId;
    private String text;
    private Boolean isComplete;
}
