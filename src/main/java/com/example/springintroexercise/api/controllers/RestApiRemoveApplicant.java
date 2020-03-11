package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.services.ApplicantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@Controller
@Transactional
public class RestApiRemoveApplicant
{
    private final ApplicantService applicantService;

    public RestApiRemoveApplicant(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @ResponseBody
    @RequestMapping("/appliers/removed")
    public String removeApplicant(@RequestParam (value = "username") String username)
    {
        applicantService.deleteApplicantByUsername(username);
        return "redirect:/appliers";
    }

}
