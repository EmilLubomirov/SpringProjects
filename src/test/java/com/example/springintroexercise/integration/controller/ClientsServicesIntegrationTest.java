package com.example.springintroexercise.integration.controller;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.models.binding.service.ServiceCreateModel;
import com.example.springintroexercise.data.models.service.clientsService.ClientsServiceServiceModel;
import com.example.springintroexercise.data.services.ClientsServiceService;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import org.hibernate.boot.cfgxml.spi.CfgXmlAccessService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.validation.BindingResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ClientsServicesIntegrationTest extends ControllerTestBase
{
    @MockBean
    BindingResult bindingResult;

    @MockBean
    ClientsServiceService clientsServiceService;

    @Test
    @WithMockUser(roles = "USER")
    public void services_onGetCall_shouldReturnView() throws Exception
    {
        mockMvc.perform(get("/services"))
                .andExpect(status().isOk())
                .andExpect(view().name("services"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void serviceNew_onGetCall_shouldReturnView() throws Exception
    {
        mockMvc.perform(get("/service-new"))
                .andExpect(status().isOk())
                .andExpect(view().name("service-new"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void serviceNew_onPostCallWithErrors_shouldRedirectToSamePage() throws Exception
    {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        mockMvc.perform(post("/service-new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/service-new"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void serviceNew_onPostCallWithServiceError_shouldRedirectToSamePage() throws Exception
    {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        Mockito.when(clientsServiceService.addService(new ClientsServiceServiceModel()))
                .thenReturn(null);

        mockMvc.perform(post("/service-new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/service-new"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void serviceNew_onPostCallSuccess_shouldRedirectToHomePage() throws Exception
    {
        ClientsServiceServiceModel model = new ClientsServiceServiceModel();
        ServiceCreateModel createModel = new ServiceCreateModel();

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(clientsServiceService.addService(model)).thenReturn(model);

        mockMvc.perform(post("/service-new")
                .flashAttr("model", createModel)
                .flashAttr("bindingResult", bindingResult))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));
    }
}
