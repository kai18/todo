package com.kaustubh.todo.model.request;

import lombok.Data;

import java.util.List;

@Data
public class TodoRequest {
    private Integer todoId;
    private String title;
    private List<TaskRequest> tasks;
}
