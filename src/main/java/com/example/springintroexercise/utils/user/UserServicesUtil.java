package com.example.springintroexercise.utils.user;

import java.util.Map;

public interface UserServicesUtil
{
    Map<String, String> getAllWithNonNullableAmount(String data);

    void addUserToServices(Map<String, String> services, String userUsername);
}
