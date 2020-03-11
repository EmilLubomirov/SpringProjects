package com.example.springintroexercise.integration.controller.authenticated;

import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.models.binding.user.UserRegisterModel;
import com.example.springintroexercise.data.repositories.UserRepository;
import com.example.springintroexercise.data.services.UserService;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.example.springintroexercise.web.filters.LoggedInUserFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class GuestControllerAuthenticatedTest extends ControllerTestBase
{
//    @Autowired
//    LoggedInUserFilter loggedInUserFilter;

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    List<User> users;
    User user;

    @Override
    protected void beforeEach()
    {
        users = new ArrayList<>();
        user = new User();
    }

    @Test
    public void register_onPostCallWithValidUser_shouldReturnLoginView() throws Exception
    {
        beforeEach();
        users.clear();

        UserRegisterModel model = new UserRegisterModel();

        model.setPassword("password");
        model.setConfirmPassword("password");
        model.setUsername("username");

        Mockito.when(userRepository.existsByUsername(model.getUsername()))
                .thenReturn(users.stream()
                        .filter(u -> u.getUsername().equals(model.getUsername()))
                        .findAny().orElse(null) != null);

        mockMvc.perform(post("/register").flashAttr("model", model))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
