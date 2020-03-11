package com.example.springintroexercise.data.services;

import com.example.springintroexercise.data.models.service.clientsService.ClientsServiceServiceModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;

import java.util.List;
import java.util.Optional;

public interface ClientsServiceService
{
    long getServicesCount();

    void seedServices();

    List<ClientsServiceServiceModel> getServices();

   Optional<ClientsServiceServiceModel> getByDescription(String description);

   ClientsServiceServiceModel addService(ClientsServiceServiceModel model);
}
