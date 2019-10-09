package com.kaustubh.todo.repository;

import com.kaustubh.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {
    Optional<Set<Todo>> findAllByUserId(Integer userId);

    Optional<Todo> findByIsDeletedFalseAndTodoId(Integer todoId);

    Optional<Set<Todo>> findAllByUserIdAndIsDeletedFalse(Integer userId);

}
