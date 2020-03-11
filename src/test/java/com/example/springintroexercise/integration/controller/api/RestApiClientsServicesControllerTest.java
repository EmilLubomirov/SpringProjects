package com.example.springintroexercise.integration.controller.api;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.repositories.ClientsServiceRepository;
import com.example.springintroexercise.data.repositories.UserRepository;
import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class RestApiClientsServicesControllerTest extends ControllerTestBase
{
    @MockBean
    AuthenticationUserService authenticationUserService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ClientsServiceRepository clientsServiceRepository;

    User user;
    List<ClientsService> clientsServices;

    ClientsService clientsService;

    @Override
    protected void beforeEach()
    {
        clientsServices = new ArrayList<>();

        clientsService = new ClientsService();
        clientsService.setDescription("Swimming pool");

        clientsServices.add(clientsService);

        user = new User();
        user.setUsername("ivchoBG");

        Mockito.when(authenticationUserService.getUserUsername())
                .thenReturn(user.getUsername());

        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(user);

        Mockito.when(clientsServiceRepository.findAll())
                .thenReturn(clientsServices);
    }

    @Test
    @WithMockUser
    public void models_onGetCallWhenNoOwnedClientsServices_shouldReturnNonOwnedClientsServicesCorrectly() throws Exception
    {
        user.setClientsServices(new HashSet<>());

        MvcResult response = mockMvc.perform(get("/api-services"))
                .andExpect(status().isOk()).andReturn();

        String actualResult = response.getResponse().getContentAsString();
        JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));

        assertEquals(1, jsonValues.size());
    }


    @Test
    @WithMockUser
    public void models_onGetCallWhenOwnedClientsService_shouldReturnZero() throws Exception
    {
        user.setClientsServices(Set.of(clientsService));

        MvcResult response = mockMvc.perform(get("/api-services"))
                .andExpect(status().isOk()).andReturn();

        String actualResult = response.getResponse().getContentAsString();
        JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));

        assertEquals(0, jsonValues.size());
    }
}
