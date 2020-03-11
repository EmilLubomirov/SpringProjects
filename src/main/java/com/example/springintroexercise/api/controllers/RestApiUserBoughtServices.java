package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import com.example.springintroexercise.utils.user.UserServicesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class RestApiUserBoughtServices {

    private final UserServicesUtil userServicesUtil;
    private final AuthenticationUserService authenticationUserService;

    public RestApiUserBoughtServices(UserServicesUtil userServicesUtil, AuthenticationUserService authenticationUserService) {
        this.userServicesUtil = userServicesUtil;
        this.authenticationUserService = authenticationUserService;
    }

    @ResponseBody
    @RequestMapping("/services/bought")
    public String getData(@RequestParam(value = "price") String price,
                          @RequestParam(value = "servicesData") String data) throws IOException
    {
        Map<String, String> namesAndAmount = userServicesUtil.getAllWithNonNullableAmount(data);
        userServicesUtil.addUserToServices(namesAndAmount, authenticationUserService.getUserUsername());

        return "redirect:/home";
    }
}
