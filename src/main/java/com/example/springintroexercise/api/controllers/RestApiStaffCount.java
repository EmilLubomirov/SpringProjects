package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.services.StaffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiStaffCount
{
    private final StaffService staffService;

    public RestApiStaffCount(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/api-staff-count")
    public long staffCount()
    {
        return staffService.count();
    }
}
