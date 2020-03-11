package com.example.springintroexercise.utils.user.validator;

import com.example.springintroexercise.data.models.binding.user.UserRegisterModel;

public interface RegisterUserUtil
{
    boolean isUserRegisterFormValid(UserRegisterModel model);

    boolean isUserRegisterDataEmpty(UserRegisterModel model);

    boolean areUserRegisterPasswordsDifferent(String password, String confirmPassword);

    boolean doesUserExist(String username);
}
