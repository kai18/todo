package com.kaustubh.todo.controller;

import com.kaustubh.todo.exception.NoDataFoundException;
import com.kaustubh.todo.exception.NoSuchResourceException;
import com.kaustubh.todo.model.User;
import com.kaustubh.todo.model.request.UserRequest;
import com.kaustubh.todo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @RequestMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) throws NoSuchResourceException {
        User user = null;
        try {
            user = userService.getUser(userId);
        } catch (NoDataFoundException e) {
            throw new NoSuchResourceException(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

}
