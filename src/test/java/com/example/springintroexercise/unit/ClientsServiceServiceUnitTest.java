package com.example.springintroexercise.unit;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.repositories.ClientsServiceRepository;
import com.example.springintroexercise.data.services.ClientsServiceService;
import com.example.springintroexercise.data.services.impl.ClientsServiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClientsServiceServiceUnitTest
{
    ClientsServiceService clientsServiceService;

    ClientsServiceRepository clientsServiceRepository;
    ModelMapper modelMapper;
    ClientsService clientsService;
    List<ClientsService> clientsServices;

    @BeforeEach
    public void init()
    {
        clientsServices = new ArrayList<>();
        clientsService = new ClientsService();

        clientsServiceRepository = Mockito.mock(ClientsServiceRepository.class);
        modelMapper = new ModelMapper();

        clientsServiceService = new ClientsServiceServiceImpl(clientsServiceRepository, modelMapper);
        Mockito.when(clientsServiceRepository.findAll()).thenReturn(clientsServices);
    }

    @Test
    public void getAll_onGetCall_shouldReturnThemCorrectly()
    {
        clientsServices.add(clientsService);
        assertEquals(1, clientsServiceService.getServices().size());
    }


    @Test
    public void getByDescription_onExistingApplicant_shouldReturnCorrectResult()
    {
        clientsServices.clear();
        clientsServices.add(clientsService);

        clientsService.setDescription("swimming pool");

        Mockito.when(clientsServiceRepository.getByDescription(clientsService.getDescription()))
                .thenReturn(clientsServices.stream().filter(s -> s.getDescription()
                        .equals(clientsService.getDescription())).findFirst().orElse(null));

        assertEquals(clientsService.getDescription(),
                clientsServiceRepository.getByDescription(clientsService.getDescription())
                        .getDescription());
    }
}
