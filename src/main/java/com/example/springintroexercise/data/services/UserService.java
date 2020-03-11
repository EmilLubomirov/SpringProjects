package com.example.springintroexercise.data.services;

import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.models.binding.user.UserRegisterModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    void update(User user);

    boolean hasUserOffers(String username);

    boolean isThereSuchUser(UserRegisterModel model);

    List<String> allUserUsername();

    boolean existsByUsername(String username);

    boolean existsBysUsernameAndPassword(String username, String password);
}
