package com.kaustubh.todo.service;

import com.kaustubh.todo.exception.NoDataFoundException;
import com.kaustubh.todo.model.Todo;
import com.kaustubh.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Set<Todo> getTodoListForUser(@NotNull Integer userId) {
        return todoRepository.findAllByUserIdAndIsDeletedFalse(userId).get();
    }

    public Todo getTodo(@NotNull Integer todoId) throws NoDataFoundException {
        Optional<Todo> todo = todoRepository.findByIsDeletedFalseAndTodoId(todoId);
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new NoDataFoundException("No Todo found with ID" + todoId);
        }
    }

    public Todo saveTodo(@NotNull Todo todo) {
        if (todo != null) {
            todo = todoRepository.save(todo);
        }
        return todo;
    }

    public Todo updateTodo(@NotNull Todo todo) throws NoDataFoundException {
        if (todoRepository.existsById(todo.getTodoId()))
            return this.saveTodo(todo);
        else
            throw new NoDataFoundException("No Todo Found with ID " + todo.getTodoId());
    }

    public Integer deleteTodo(@NotNull Integer todoId) throws NoDataFoundException {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isPresent()) {
            Todo todo1 = todo.get();
            todo1.setIsDeleted(Boolean.TRUE);
            todoRepository.save(todo1);
        } else {
            throw new NoDataFoundException("No Todo found with ID " + todoId);
        }
        return todoId;
    }

}
