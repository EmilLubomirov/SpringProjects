package com.example.springintroexercise.integration.controller.api;


import com.example.springintroexercise.data.entities.Applicant;
import com.example.springintroexercise.data.repositories.ApplicantRepository;
import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestApiAppliersHiredControllerTest extends ControllerTestBase
{
    @MockBean
    ApplicantRepository applicantRepository;

    @Autowired
    ApplicantService applicantService;

    Applicant applicant;

    @Override
    protected void beforeEach()
    {
        applicant = new Applicant();
        applicant.setUsername("catcher.com");

        Mockito.when(applicantRepository.findByUsername(applicant.getUsername()))
                .thenReturn(applicant);
    }

    @Test
    @WithMockUser(roles = "ROOT")
    public void appliersHired_onGetCall_shouldRedirectToAppliersSuccessfully() throws Exception
    {
        MvcResult response = mockMvc.perform(get("/appliers/hired").param("userUsername",
                applicant.getUsername()))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = response.getResponse().getContentAsString();
        assertEquals("redirect:/appliers", contentAsString);

    }
}
