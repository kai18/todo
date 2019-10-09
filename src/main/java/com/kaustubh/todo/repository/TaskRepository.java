package com.kaustubh.todo.repository;

import com.kaustubh.todo.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    Task findByTextAndTodoTodoId(String text, Integer todoId);

    Task findByIsDeletedFalseAndAndTaskId(Integer taskId);

    Task findByTextEqualsIgnoreCaseAndIsDeletedFalseAndTodoTodoId(String text, Integer todoId);
}
