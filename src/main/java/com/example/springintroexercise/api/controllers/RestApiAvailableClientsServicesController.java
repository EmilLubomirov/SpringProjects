package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.models.service.clientsService.ClientsServiceServiceModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import com.example.springintroexercise.data.models.view.ClientsServiceViewModel;
import com.example.springintroexercise.data.services.ClientsServiceService;
import com.example.springintroexercise.data.services.UserService;
import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class RestApiAvailableClientsServicesController
{
    private final AuthenticationUserService authenticationUserService;
    private final ClientsServiceService clientsServiceService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RestApiAvailableClientsServicesController(AuthenticationUserService authenticationUserService,
                                                     ClientsServiceService clientsServiceService, UserService userService, ModelMapper modelMapper) {
        this.authenticationUserService = authenticationUserService;
        this.clientsServiceService = clientsServiceService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api-services")
    public List<ClientsServiceViewModel> models()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        UserServiceModel model = userService.findByUsername(authenticationUserService.getUserUsername());

        List<ClientsServiceViewModel> collect = clientsServiceService.getServices()
                .stream()
                .filter(s -> {

                    Set<ClientsServiceServiceModel> clientsServices = model.getClientsServices();

                    for (ClientsServiceServiceModel clientsService : clientsServices) {

                        if (clientsService.getDescription().equals(s.getDescription())) {
                            return false;
                        }
                    }

                    return true;
                })
                .map(s -> modelMapper.map(s, ClientsServiceViewModel.class))
                .collect(Collectors.toList());

        return collect;
    }
}
