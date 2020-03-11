package com.example.springintroexercise.data.services;

import com.example.springintroexercise.data.models.service.applicant.ApplicantServiceModel;

import java.util.List;

public interface ApplicantService
{
    ApplicantServiceModel addApplicant(ApplicantServiceModel model);

    boolean existsByUsername(String username);

    List<ApplicantServiceModel> getAll();

    void deleteApplicantByUsername(String username);
}
