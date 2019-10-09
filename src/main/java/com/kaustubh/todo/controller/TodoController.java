package com.kaustubh.todo.controller;

import com.kaustubh.todo.exception.ApiException;
import com.kaustubh.todo.exception.NoDataFoundException;
import com.kaustubh.todo.exception.NoSuchResourceException;
import com.kaustubh.todo.model.Task;
import com.kaustubh.todo.model.Todo;
import com.kaustubh.todo.model.request.TaskRequest;
import com.kaustubh.todo.model.request.TodoRequest;
import com.kaustubh.todo.service.TodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user/{userId}/todo")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public static Todo copyToTodoFromRequest(Integer userId, TodoRequest request) {

        Todo todo = new Todo();
        todo.setUserId(userId);
        List<Task> tasks = new ArrayList<>();
        todo.setTasks(tasks);
        if (request != null && todo.getTodoId() == null) {
            BeanUtils.copyProperties(request, todo, "tasks");
            if (request.getTasks() != null) {
                for (TaskRequest taskRequest : request.getTasks()) {
                    Task task = new Task();
                    BeanUtils.copyProperties(taskRequest, task);
                    tasks.add(task);
                }
            }
        }
        return todo;
    }

    @GetMapping
    @RequestMapping(path = "/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer todoId) throws NoSuchResourceException, ApiException {
        Todo todo = null;
        try {
            todo = todoService.getTodo(todoId);
        } catch (NoDataFoundException e) {
            throw new NoSuchResourceException(e.getMessage());
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@PathVariable Integer userId, @RequestBody TodoRequest request) throws Exception {
        Todo todo = copyToTodoFromRequest(userId, request);
        todoService.saveTodo(todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteTodo(@PathVariable Integer todoId) {
        try {
            todoService.deleteTodo(todoId);
        } catch (NoDataFoundException e) {
            new NoSuchResourceException(e.getMessage());
        }
        return ResponseEntity.ok(todoId);
    }

    @PutMapping
    public ResponseEntity<Todo> editTodo(@PathVariable Integer userId, @RequestBody TodoRequest request) throws NoSuchResourceException, ApiException {
        Todo todo = copyToTodoFromRequest(userId, request);

        try {
            todoService.updateTodo(todo);
        } catch (NoDataFoundException e) {
            throw new NoSuchResourceException(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
        return ResponseEntity.ok(todo);
    }
}
