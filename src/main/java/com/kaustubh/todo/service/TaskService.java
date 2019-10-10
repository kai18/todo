package com.kaustubh.todo.service;

import com.kaustubh.todo.exception.NoDataFoundException;
import com.kaustubh.todo.model.Task;
import com.kaustubh.todo.model.Todo;
import com.kaustubh.todo.repository.TaskRepository;
import com.kaustubh.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class TaskService {

    private TodoRepository todoRepository;
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TodoRepository todoRepository, TaskRepository taskRepository) {
        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
    }

    public Todo addTask(@NotNull Integer todoId, @NotNull Task task) throws DuplicateKeyException {
        if (taskRepository.findByTextAndTodoTodoId(task.getText(), todoId) == null) {
            Todo todo = todoRepository.findById(todoId).get();
            todo.getTasks().add(task);
            todoRepository.save(todo);
            return todo;
        } else {
            throw new DuplicateKeyException("Task already present in TODO");
        }
    }

    public Todo updateTask(@NotNull Integer todoId, @NotNull Task task) throws DuplicateKeyException {
        if (taskRepository.findById(task.getTaskId()) != null) {
            Todo todo = todoRepository.findById(todoId).get();
            task.setTodo(todo);
            taskRepository.save(task);
            return todo;
        } else {
            throw new DuplicateKeyException("Task doesn't exist");
        }
    }

    public Integer deleteTask(@NotNull Integer taskId) throws NoDataFoundException {
        if (taskRepository.findByIsDeletedFalseAndAndTaskId(taskId) != null) {
            Task task = taskRepository.findById(taskId).get();
            task.setIsDeleted(Boolean.TRUE);
            taskRepository.save(task);
            return task.getTaskId();
        } else {
            throw new NoDataFoundException("No Task exists with ID " + taskId);
        }
    }
}
