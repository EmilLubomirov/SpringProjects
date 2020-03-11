package com.example.springintroexercise.web.controllers;

import com.example.springintroexercise.data.models.binding.service.ServiceCreateModel;
import com.example.springintroexercise.data.models.service.clientsService.ClientsServiceServiceModel;
import com.example.springintroexercise.data.services.ClientsServiceService;
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
import java.util.List;

@Controller
public class ClientsServicesController
{
    private final ClientsServiceService clientsServiceService;
    private final ModelMapper modelMapper;

    public ClientsServicesController(ClientsServiceService clientsServiceService, ModelMapper modelMapper) {
        this.clientsServiceService = clientsServiceService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/services")
    @SuppressWarnings(value = "unchecked")
    public String services(HttpSession session)
    {
        if (!((boolean) session.getAttribute("hasUserOffers")) &&
                !((List<String>) session.getAttribute("roles")).contains("ADMIN"))
        {
            return "error";
        }

        if (clientsServiceService.getServicesCount() == 0)
        {
            clientsServiceService.seedServices();
        }

        return "services";
    }

    @GetMapping("/service-new")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ModelAndView serviceNew(@ModelAttribute ServiceCreateModel model, ModelAndView modelAndView)
    {
        modelAndView.setViewName("service-new");
        modelAndView.addObject("model", model);
        return modelAndView;
    }

    @PostMapping("/service-new")
    public String serviceNewConfirm(@Valid @ModelAttribute ServiceCreateModel model,
                                    BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "redirect:/service-new";
        }

        ClientsServiceServiceModel serviceModel = modelMapper.map(model, ClientsServiceServiceModel.class);

        if (clientsServiceService.addService(serviceModel) == null)
        {
            return "redirect:/service-new";
        }

        return "redirect:/home";
    }
}
