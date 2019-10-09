package com.kaustubh.todo.service;

import com.kaustubh.todo.exception.NoDataFoundException;
import com.kaustubh.todo.model.User;
import com.kaustubh.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@NotNull User user) throws DuplicateKeyException {
        if (userRepository.existsUserByEmail(user.getEmail()))
            throw new DuplicateKeyException("User already exists with email id " + user.getEmail());
        return userRepository.save(user);
    }

    public User getUser(@NotNull Integer userId) throws NoDataFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoDataFoundException("No user found");
        }
    }
}
