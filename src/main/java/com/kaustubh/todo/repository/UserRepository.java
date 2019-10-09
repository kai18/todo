package com.kaustubh.todo.repository;

import com.kaustubh.todo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsUserByEmail(String email);
}
