package com.example.springintroexercise.web.controllers;

import com.example.springintroexercise.data.models.binding.user.ApplicantModel;
import com.example.springintroexercise.data.models.service.applicant.ApplicantServiceModel;
import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.data.services.StaffService;
import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import com.example.springintroexercise.enums.Position;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController
{
    private final ModelMapper modelMapper;
    private final ApplicantService applicantService;
    private final AuthenticationUserService authenticationUserService;
    private final StaffService staffService;

    public UserController(ModelMapper modelMapper, ApplicantService applicantService,
                          AuthenticationUserService authenticationUserService, StaffService staffService) {
        this.modelMapper = modelMapper;
        this.applicantService = applicantService;
        this.authenticationUserService = authenticationUserService;
        this.staffService = staffService;
    }


    @GetMapping("/home")
    public String home()
    {
        return "home";
    }

    @GetMapping("/offers")
    public ModelAndView offers(ModelAndView modelAndView, HttpSession session)
    {
        modelAndView.setViewName("offers");
        modelAndView.addObject("roles", session.getAttribute("roles"));
        return modelAndView;
    }

    @GetMapping("/join-us")
    public ModelAndView joinUs(@ModelAttribute ApplicantModel model,
                               ModelAndView modelAndView, HttpSession session)
    {
        if (((boolean) session.getAttribute("hasApplied")))
        {
            modelAndView.setViewName("error");
            return modelAndView;
        }

        modelAndView.setViewName("join-us");
        modelAndView.addObject("model", model);
        return modelAndView;
    }

    @PostMapping("/join-us")
    public String joinUsConfirm(@Valid @ModelAttribute ApplicantModel model,
                                BindingResult bindingResult)
    {

        if (bindingResult.hasErrors())
        {
            return "redirect:/join-us";
        }


        ApplicantServiceModel serviceModel = modelMapper.map(model, ApplicantServiceModel.class);
        serviceModel.setRequestedPosition(Position.valueOf(model.getRequestedPosition().toUpperCase()));
        serviceModel.setUsername(authenticationUserService.getUserUsername());

        applicantService.addApplicant(serviceModel);

        return "redirect:/home";
    }

    @GetMapping("/appliers")
    @PreAuthorize(value = "hasAuthority('ROOT')")
    public String appliers()
    {
        return "appliers";
    }

    @GetMapping("/staff")
    @PreAuthorize(value = "hasAuthority('ROOT')")
    public String staff()
    {
        if (staffService.count() == 0)
        {
            staffService.seedStaff();
        }

        return "staff";
    }
}
