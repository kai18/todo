package com.kaustubh.todo.repository;

import com.kaustubh.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {

    Optional<Todo> findByIsDeletedAndTodoId(Boolean isDeleted, Integer todoId);

    Optional<List<Todo>> findAllByUserIdAndIsDeletedFalse(Integer userId);

}
