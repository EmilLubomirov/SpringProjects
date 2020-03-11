package com.example.springintroexercise.data.services.impl;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.models.service.clientsService.ClientsServiceServiceModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import com.example.springintroexercise.data.repositories.ClientsServiceRepository;
import com.example.springintroexercise.data.services.ClientsServiceService;
import com.sun.xml.bind.v2.TODO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientsServiceServiceImpl implements ClientsServiceService
{
    private final ClientsServiceRepository clientsServiceRepository;
    private final ModelMapper modelMapper;

    public ClientsServiceServiceImpl(ClientsServiceRepository clientsServiceRepository, ModelMapper modelMapper) {
        this.clientsServiceRepository = clientsServiceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long getServicesCount()
    {
        return clientsServiceRepository.count();
    }

    @Override
    public void seedServices()
    {
        ClientsService swimmingPool = new ClientsService("Swimming pool", new BigDecimal("40.00"));
        ClientsService fitness = new ClientsService("Fitness", new BigDecimal("30.00"));
        ClientsService bodyMassage = new ClientsService("Body massage", new BigDecimal("50.00"));

        clientsServiceRepository.saveAndFlush(swimmingPool);
        clientsServiceRepository.saveAndFlush(fitness);
        clientsServiceRepository.saveAndFlush(bodyMassage);
    }

    @Override
    public List<ClientsServiceServiceModel> getServices()
    {
        return clientsServiceRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, ClientsServiceServiceModel.class))
                .collect(Collectors.toList());
    }

    public Optional<ClientsServiceServiceModel> getByDescription(String description)
    {
        return Optional.of(modelMapper.map(clientsServiceRepository.getByDescription(description),
                ClientsServiceServiceModel.class));
    }


    @Override
    public ClientsServiceServiceModel addService(ClientsServiceServiceModel model)
    {
        ClientsService service = clientsServiceRepository.saveAndFlush(modelMapper.map(model,
                ClientsService.class));

        return modelMapper.map(service, ClientsServiceServiceModel.class);
    }
}
