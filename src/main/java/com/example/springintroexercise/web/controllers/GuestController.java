package com.example.springintroexercise.web.controllers;

import com.example.springintroexercise.data.models.binding.user.UserRegisterModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import com.example.springintroexercise.data.services.UserService;
import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import com.example.springintroexercise.utils.user.validator.RegisterUserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestController
{
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationUserService authenticationUserService;
    private final RegisterUserUtil registerUserUtil;

    public GuestController(UserService userService, ModelMapper modelMapper, AuthenticationUserService authenticationUserService, RegisterUserUtil registerUserUtil) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationUserService = authenticationUserService;
        this.registerUserUtil = registerUserUtil;
    }

    @GetMapping("/")
    public String index()
    {
        if (!authenticationUserService.getUserUsername().equals("anonymousUser"))
        {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute UserRegisterModel model)
    {
        boolean shouldRedirect = registerUserUtil.isUserRegisterFormValid(model);

        if (shouldRedirect)
        {
            userService.register(modelMapper.map(model, UserServiceModel.class));
            return "redirect:/login";
        }

        return "register";
    }


    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
