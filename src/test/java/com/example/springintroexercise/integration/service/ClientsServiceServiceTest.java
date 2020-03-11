package com.example.springintroexercise.integration.service;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.repositories.ClientsServiceRepository;
import com.example.springintroexercise.data.services.ClientsServiceService;
import com.example.springintroexercise.integration.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientsServiceServiceTest extends TestBase
{
    @Autowired
    ClientsServiceService clientsServiceService;

    @MockBean
    ClientsServiceRepository clientsServiceRepository;

    ClientsService clientsService;
    List<ClientsService> clientsServices;

    @BeforeEach
    public void init()
    {
        clientsServices = new ArrayList<>();
        clientsService = new ClientsService();

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
        clientsService.setDescription("swimming pool");
        clientsServices.add(clientsService);

        Mockito.when(clientsServiceRepository.getByDescription("swimming pool"))
                .thenReturn(clientsServices.stream().filter(c -> c.getDescription()
                        .equals("swimming pool")).findFirst().orElse(null));

        assertEquals(clientsService.getDescription(), clientsServiceRepository
                .getByDescription("swimming pool").getDescription());
    }

    @Test
    public void getByDescription_onNONExistingApplicant_shouldReturnNull()
    {
        clientsServices.clear();

        Mockito.when(clientsServiceRepository.getByDescription("swimming pool"))
                .thenReturn(clientsServices.stream().filter(c -> c.getDescription()
                        .equals("swimming pool")).findFirst().orElse(null));

        assertNull(clientsServiceRepository.getByDescription("swimming pool"));
    }
}
