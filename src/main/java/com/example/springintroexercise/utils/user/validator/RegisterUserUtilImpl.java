package com.example.springintroexercise.utils.user.validator;

import com.example.springintroexercise.data.models.binding.user.UserRegisterModel;
import com.example.springintroexercise.data.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RegisterUserUtilImpl implements RegisterUserUtil
{
    @Autowired
    private UserService userService;


    @Override
    public boolean isUserRegisterFormValid(UserRegisterModel model)
    {
        return !isUserRegisterDataEmpty(model) &&
                !areUserRegisterPasswordsDifferent(model.getPassword(), model.getConfirmPassword()) &&
                ! doesUserExist(model.getUsername());
    }

    @Override
    public boolean isUserRegisterDataEmpty(UserRegisterModel userRegisterModel)
    {
        return userRegisterModel.getUsername().isBlank() ||
                userRegisterModel.getPassword().isBlank() ||
                userRegisterModel.getConfirmPassword().isBlank();
    }

    @Override
    public boolean areUserRegisterPasswordsDifferent(String password, String confirmPassword)
    {
        return !password.equals(confirmPassword);
    }

    @Override
    public boolean doesUserExist(String username)
    {
        return userService.existsByUsername(username);
    }
}
