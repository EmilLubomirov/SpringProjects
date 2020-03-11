package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.models.service.staff.StaffViewModel;
import com.example.springintroexercise.data.services.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestApiStaff
{
    private final StaffService staffService;
    private final ModelMapper modelMapper;

    public RestApiStaff(StaffService staffService, ModelMapper modelMapper) {
        this.staffService = staffService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api-staff")
    public List<StaffViewModel> staff()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return staffService.getAll()
                .stream()
                .map(s -> modelMapper.map(s, StaffViewModel.class))
                .collect(Collectors.toList());
    }
}
