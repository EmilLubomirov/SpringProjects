package com.example.springintroexercise.integration.controller;

import com.example.springintroexercise.data.models.binding.user.UserRegisterModel;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.example.springintroexercise.utils.user.validator.RegisterUserUtil;
import com.example.springintroexercise.web.filters.LoggedInUserFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class GuestControllerTest extends ControllerTestBase
{
    @Autowired
    LoggedInUserFilter loggedInUserFilter;


    @Test
    public void register_onGetCall_shouldReturnRegisterView() throws Exception
    {
        mockMvc.perform(get("/register")).andExpect(status().isOk())
                .andExpect(view().name("register"));
    }


    @Test
    public void login_onGetCall_shouldReturnLoginView() throws Exception
    {
        mockMvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void index_onNONExistingUser_shouldReturnIndexView() throws Exception
    {
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void index_onExistingUser_shouldReturnHomeView() throws Exception
    {
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));
    }

}
