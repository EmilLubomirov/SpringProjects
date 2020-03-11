package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.entities.Applicant;
import com.example.springintroexercise.data.models.service.staff.StaffServiceModel;
import com.example.springintroexercise.data.repositories.ApplicantRepository;
import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.data.services.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@Transactional
public class RestApiAppliersHired
{
    private final ApplicantService applicantService;
    private final ModelMapper modelMapper;
    private final ApplicantRepository applicantRepository;
    private final StaffService staffService;

    public RestApiAppliersHired(ApplicantService applicantService, ModelMapper modelMapper, ApplicantRepository applicantRepository, StaffService staffService) {
        this.applicantService = applicantService;
        this.modelMapper = modelMapper;
        this.applicantRepository = applicantRepository;
        this.staffService = staffService;
    }

    @ResponseBody
    @RequestMapping("/appliers/hired")
    public String appliersHired(@RequestParam(value = "userUsername") String userUsername)
    {
        if (staffService.count() == 0)
        {
            staffService.seedStaff();
        }

        Applicant applicant = applicantRepository.findByUsername(userUsername);
        StaffServiceModel model = modelMapper.map(applicant, StaffServiceModel.class);

        staffService.addStaff(model);
        applicantService.deleteApplicantByUsername(userUsername);

       return  "redirect:/appliers";
    }

}
