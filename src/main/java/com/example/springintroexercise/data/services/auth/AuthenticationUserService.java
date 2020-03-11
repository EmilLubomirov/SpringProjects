package com.example.springintroexercise.data.services.auth;

import java.util.List;

public interface AuthenticationUserService
{
    String getUserUsername();

    List<String> getUserRoles();
}
