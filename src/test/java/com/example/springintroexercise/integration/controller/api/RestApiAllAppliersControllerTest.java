package com.example.springintroexercise.integration.controller.api;

import com.example.springintroexercise.data.entities.Applicant;
import com.example.springintroexercise.data.repositories.ApplicantRepository;
import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestApiAllAppliersControllerTest extends ControllerTestBase
{
    @MockBean
    ApplicantRepository applicantRepository;

    @Autowired
    ApplicantService applicantService;

    List<Applicant> applicants;

    Applicant applicant;

    @Override
    protected void beforeEach()
    {
        applicants = new ArrayList<>();
        applicant = new Applicant();
        applicant.setUsername("Ivan");

        Mockito.when(applicantRepository.findAll())
                .thenReturn(applicants);
    }

    @Test
    @WithMockUser(roles = "ROOT")
    public void appliers_onGetCallWithSomeAppliers_shouldReturnThemCorrectly() throws Exception
    {
        applicants.clear();
        applicants.add(applicant);

        MvcResult response = mockMvc.perform(get("/api-appliers"))
                .andExpect(status().isOk()).andReturn();

        String actualResult = response.getResponse().getContentAsString();

        JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));
        assertEquals(applicants.size(), jsonValues.size());
    }

    @Test
    @WithMockUser(roles = "ROOT")
    public void appliers_onGetCallWithNOAppliers_shouldReturnEmptyJSON() throws Exception
    {
        applicants.clear();

        MvcResult response = mockMvc.perform(get("/api-appliers"))
                .andExpect(status().isOk()).andReturn();

        String actualResult = response.getResponse().getContentAsString();

        JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));
        assertEquals(applicants.size(), jsonValues.size());
    }



}
