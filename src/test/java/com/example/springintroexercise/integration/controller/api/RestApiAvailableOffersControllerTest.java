package com.example.springintroexercise.integration.controller.api;

import com.example.springintroexercise.data.entities.Offer;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.repositories.OfferRepository;
import com.example.springintroexercise.data.services.OfferService;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestApiAvailableOffersControllerTest extends ControllerTestBase
{
    List<Offer> offers;

    @MockBean
    OfferRepository offerRepository;

    @Autowired
    OfferService offerService;

    @Override
    protected void beforeEach()
    {
        offers = new ArrayList<>();

        Mockito.when(offerRepository.findAll())
                .thenReturn(offers);
    }

    @Test
    @WithMockUser
    public void models_onGetCallWithAvailableOffers_shouldReturnThem() throws Exception
    {
        offers.clear();

        Offer offer = new Offer();
        offer.setDepartmentRent(new BigDecimal("34.56"));
        offer.setApartmentType("single");
        offer.setAgencyCommission(new BigDecimal("3.45"));
        offers.add(offer);

        MvcResult mvcResult = mockMvc.perform(get("/api-offers"))
                .andExpect(status().isOk()).andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));
        assertEquals(offers.size(), jsonValues.size());
    }

    @Test
    @WithMockUser
    public void models_onGetCallWithNOAvailableOffers_shouldReturnEmptyJSON() throws Exception
    {
        offers.clear();

        Offer offer = new Offer();
        offer.setDepartmentRent(new BigDecimal("34.56"));
        offer.setApartmentType("single");
        offer.setAgencyCommission(new BigDecimal("3.45"));
        offer.setUser(new User());

        offers.add(offer);

        MvcResult mvcResult = mockMvc.perform(get("/api-offers"))
                .andExpect(status().isOk()).andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));
        assertEquals(0, jsonValues.size());
    }


}
