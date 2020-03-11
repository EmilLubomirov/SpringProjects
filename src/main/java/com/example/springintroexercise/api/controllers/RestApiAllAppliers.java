package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.models.view.AppliersViewModel;
import com.example.springintroexercise.data.services.ApplicantService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestApiAllAppliers
{
    private final ApplicantService applicantService;
    private final ModelMapper modelMapper;

    public RestApiAllAppliers(ApplicantService applicantService, ModelMapper modelMapper) {
        this.applicantService = applicantService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api-appliers")
    public List<AppliersViewModel> appliers()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return applicantService.getAll()
                .stream()
                .map(a -> modelMapper.map(a, AppliersViewModel.class))
                .collect(Collectors.toList());
    }
}
