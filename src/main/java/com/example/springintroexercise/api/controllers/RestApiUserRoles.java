package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiUserRoles
{
    private final AuthenticationUserService authenticationUserService;

    public RestApiUserRoles(AuthenticationUserService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }

    @ResponseBody
    @GetMapping("/api-userRoles")
    public List<String> userRoles()
    {
        return authenticationUserService.getUserRoles();
    }
}
