package com.example.springintroexercise.data.services.impl;

import com.example.springintroexercise.data.entities.Applicant;
import com.example.springintroexercise.data.models.service.applicant.ApplicantServiceModel;
import com.example.springintroexercise.data.repositories.ApplicantRepository;
import com.example.springintroexercise.data.services.ApplicantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements ApplicantService
{
    private final ApplicantRepository applicantRepository;
    private final ModelMapper modelMapper;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ModelMapper modelMapper) {
        this.applicantRepository = applicantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApplicantServiceModel addApplicant(ApplicantServiceModel model)
    {
        Applicant applicant = applicantRepository.saveAndFlush(modelMapper.map(model, Applicant.class));
        return modelMapper.map(applicant, ApplicantServiceModel.class);
    }

    @Override
    public boolean existsByUsername(String username)
    {
        return applicantRepository.existsByUsername(username);
    }

    @Override
    public List<ApplicantServiceModel> getAll()
    {
        return applicantRepository.findAll()
                .stream()
                .map(a -> modelMapper.map(a, ApplicantServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteApplicantByUsername(String username)
    {
        applicantRepository.deleteByUsername(username);
    }

}
