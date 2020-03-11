package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiAllUserUsernameData
{
    private final UserService userService;

    public RestApiAllUserUsernameData(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api-allUsername")
    public List<String> username()
    {
        return userService.allUserUsername();
    }
}
