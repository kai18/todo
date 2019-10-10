package com.kaustubh.todo.controller;

import com.kaustubh.todo.exception.ApiException;
import com.kaustubh.todo.exception.NoDataFoundException;
import com.kaustubh.todo.exception.NoSuchResourceException;
import com.kaustubh.todo.model.Todo;
import com.kaustubh.todo.model.request.TodoRequest;
import com.kaustubh.todo.service.TodoService;
import com.kaustubh.todo.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user/{userId}/todo")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
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

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoForUser(@PathVariable Integer userId)
            throws NoSuchResourceException, ApiException {
        List<Todo> todos = null;
        try {
            todos = todoService.getTodoListForUser(userId);
        } catch (NoDataFoundException e) {
            throw new NoSuchResourceException(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }

        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@PathVariable Integer userId, @RequestBody TodoRequest request) {
        Todo todo = ControllerUtil.copyToTodoFromRequest(userId, request);
        todoService.saveTodo(todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping
    @RequestMapping(path = "/{todoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteTodo(@PathVariable Integer todoId) throws NoSuchResourceException, ApiException {
        try {
            todoService.deleteTodo(todoId);
        } catch (NoDataFoundException e) {
            throw new NoSuchResourceException(e.getMessage());
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
        return ResponseEntity.ok(todoId);
    }

    @PutMapping
    public ResponseEntity<Todo> editTodo(@PathVariable Integer userId, @RequestBody TodoRequest request)
            throws NoSuchResourceException, ApiException {
        Todo todo = ControllerUtil.copyToTodoFromRequest(userId, request);

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
