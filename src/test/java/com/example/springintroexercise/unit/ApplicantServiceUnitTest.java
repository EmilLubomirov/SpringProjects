package com.example.springintroexercise.unit;

import com.example.springintroexercise.data.entities.Applicant;
import com.example.springintroexercise.data.repositories.ApplicantRepository;
import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.data.services.impl.ApplicantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantServiceUnitTest
{
    ApplicantService applicantService;

    ApplicantRepository applicantRepository;
    ModelMapper modelMapper;
    List<Applicant> applicants;
    Applicant applicant;

    @BeforeEach
    public void init()
    {
        applicants = new ArrayList<>();
        applicant = new Applicant();
        applicant.setUsername("atanas");

        applicantRepository = Mockito.mock(ApplicantRepository.class);
        modelMapper = new ModelMapper();

        applicantService = new ApplicantServiceImpl(applicantRepository, modelMapper);

        Mockito.when(applicantRepository.findAll()).thenReturn(applicants);
    }

    @Test
    public void existsByUsername_onExistingApplicant_shouldReturnTrue()
    {
        applicants.clear();
        applicants.add(applicant);

        Mockito.when(applicantRepository.existsByUsername(applicant.getUsername()))
                .thenReturn(applicants.stream().filter(a -> a.getUsername()
                        .equals(applicant.getUsername())).findFirst().orElse(null) != null);

        assertTrue(applicantService.existsByUsername(applicant.getUsername()));
    }

    @Test
    public void existsByUsername_onNONExistingApplicant_shouldReturnFalse()
    {
        applicants.clear();

        Mockito.when(applicantRepository.existsByUsername(applicant.getUsername()))
                .thenReturn(applicants.stream().filter(a -> a.getUsername()
                        .equals(applicant.getUsername())).findFirst().orElse(null) != null);

        assertFalse(applicantService.existsByUsername(applicant.getUsername()));
    }

    @Test
    public void getAll_onGetCall_shouldReturnActualSize()
    {
        applicants.clear();
        applicants.add(applicant);

        assertEquals(1, applicantService.getAll().size());
    }
}
