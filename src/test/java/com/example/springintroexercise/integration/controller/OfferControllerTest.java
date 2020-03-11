package com.example.springintroexercise.integration.controller;

import com.example.springintroexercise.data.models.binding.offer.RegisterOfferModel;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.example.springintroexercise.web.filters.LoggedInUserFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class OfferControllerTest extends ControllerTestBase
{
    @Autowired
    LoggedInUserFilter loggedInUserFilter;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void accessingPage_onInvalidURL_shouldReturnStatus404() throws Exception
    {
        RegisterOfferModel model = new RegisterOfferModel();

        this.mockMvc.perform(get("/offer-reg"))
        .andExpect(status().isOk())
                .andExpect(view().name("offer-register"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void noOfferFound_onGetCall_shouldReturnView() throws Exception
    {

        this.mockMvc.perform(get("/no-result"))
                .andExpect(status().isOk())
                .andExpect(view().name("no-offer-found"));
    }


}
