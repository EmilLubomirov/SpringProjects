package com.example.springintroexercise.integration.service;

import com.example.springintroexercise.data.entities.Applicant;
import com.example.springintroexercise.data.repositories.ApplicantRepository;
import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.integration.TestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantServiceIntegrationTest extends TestBase
{
    @Autowired
    ApplicantService applicantService;

    @MockBean
    ApplicantRepository applicantRepository;

    List<Applicant> applicants;
    Applicant applicant;

    @Override
    protected void beforeEach()
    {
        applicants = new ArrayList<>();
        applicant = new Applicant();
        applicant.setUsername("atanas");

        Mockito.when(applicantRepository.findAll())
                .thenReturn(applicants);
    }

    @Test
    public void existsByUsername_onExistingApplicant_shouldReturnTrue()
    {
        applicants.clear();
        applicants.add(applicant);

        Mockito.when(applicantRepository.existsByUsername("atanas"))
                .thenReturn(applicants.stream().map(Applicant::getUsername)
                        .collect(Collectors.toList()).contains(applicant.getUsername()));

        assertTrue(applicantService.existsByUsername(applicant.getUsername()));
    }

    @Test
    public void existsByUsername_onNONExistingApplicant_shouldReturnFalse()
    {
        applicants.clear();

        Mockito.when(applicantRepository.existsByUsername("atanas"))
                .thenReturn(applicants.stream().map(Applicant::getUsername)
                        .collect(Collectors.toList()).contains(applicant.getUsername()));

        assertFalse(applicantService.existsByUsername(applicant.getUsername()));
    }

    @Test
    public void getAll_onGetCall_shouldGetThemCorrectly()
    {
        applicants.clear();
        applicants.add(applicant);

        assertEquals(1, applicantService.getAll().size());
    }
}
