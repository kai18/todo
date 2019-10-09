package com.kaustubh.todo.controller;

import com.kaustubh.todo.exception.ApiException;
import com.kaustubh.todo.exception.NoDataFoundException;
import com.kaustubh.todo.exception.NoSuchResourceException;
import com.kaustubh.todo.model.Task;
import com.kaustubh.todo.model.Todo;
import com.kaustubh.todo.model.request.TaskRequest;
import com.kaustubh.todo.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/{userId}/todo/{todoId}/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Todo> addTask(@PathVariable Integer todoId, @RequestBody TaskRequest taskRequest) {
        Task task = new Task();
        BeanUtils.copyProperties(taskRequest, task);
        Todo todo = taskService.addTask(todoId, task);
        return ResponseEntity.ok(todo);
    }

    @PutMapping
    public ResponseEntity<Todo> editTask(@PathVariable Integer todoId, @RequestBody TaskRequest taskRequest) {
        Task task = new Task();
        BeanUtils.copyProperties(taskRequest, task);
        Todo todo = taskService.updateTask(todoId, task);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping
    @RequestMapping("/{taskId}")
    public ResponseEntity<Integer> deleteTask(@PathVariable Integer todoId, @PathVariable Integer taskId)
            throws NoSuchResourceException, ApiException {
        try {
            taskService.deleteTask(taskId);
        } catch (NoDataFoundException e) {
            throw new NoSuchResourceException(e.getMessage());
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
        return ResponseEntity.ok(taskId);
    }
}
