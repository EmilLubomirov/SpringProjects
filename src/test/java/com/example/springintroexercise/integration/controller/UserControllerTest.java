package com.example.springintroexercise.integration.controller;

import com.example.springintroexercise.data.models.binding.user.ApplicantModel;
import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.example.springintroexercise.web.filters.LoggedInUserFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser(roles = "USER")
public class UserControllerTest extends ControllerTestBase
{
    @Autowired
    LoggedInUserFilter loggedInUserFilter;

    @MockBean
    ApplicantService applicantService;


    @Test
    public void home_onGetCall_shouldReturnHomeView() throws Exception
    {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void joinUs_onGetWhenNOTJoinedCall_shouldReturnJoinUsView() throws Exception
    {
        Mockito.when(applicantService.existsByUsername("Ivan")).thenReturn(false);

        mockMvc.perform(get("/join-us"))
                .andExpect(status().isOk())
                .andExpect(view().name("join-us"));
    }


}
