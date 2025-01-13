package com.elearn.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.dtos.UserDto;
import com.elearn.app.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto){

        return userService.create(userDto);

    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable String userId){

        return userService.getById(userId);

    }

}
