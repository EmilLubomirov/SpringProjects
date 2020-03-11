package com.example.springintroexercise.unit;

import com.example.springintroexercise.data.entities.Offer;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.models.binding.offer.FindOfferModel;
import com.example.springintroexercise.data.models.service.offer.OfferServiceModel;
import com.example.springintroexercise.data.repositories.OfferRepository;
import com.example.springintroexercise.data.repositories.UserRepository;
import com.example.springintroexercise.data.services.OfferService;
import com.example.springintroexercise.data.services.impl.OfferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class OfferServiceUnitTest
{
    OfferService offerService;

    List<Offer> offers;
    OfferRepository offerRepository;
    ModelMapper modelMapper;
    UserRepository userRepository;
    User user;

    @BeforeEach
    public void init()
    {
        offers = new ArrayList<>();

        user = new User();

        offerRepository = Mockito.mock(OfferRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        modelMapper = new ModelMapper();
        offerService = new OfferServiceImpl(offerRepository, userRepository, modelMapper);

        Mockito.when(offerRepository.findAll())
                .thenReturn(offers);
    }

    @Test
    public void findOffer_onFoundAppropriateOffer_shouldGetIt()
    {
        offers.clear();

        FindOfferModel model = new FindOfferModel();
        model.setApartmentType("family");
        model.setFamilyBudget(new BigDecimal("44.55"));

        Offer offer = new Offer();
        offer.setId("demo");
        offer.setAgencyCommission(new BigDecimal("3.45"));
        offer.setApartmentType("family");
        offer.setDepartmentRent(new BigDecimal("34.56"));

        offers.add(offer);
        OfferServiceModel found = offerService.findAppropriateOffer(model).orElse(null);

        assert found != null;
        assertEquals(offer.getId(), found.getId());

    }

    @Test
    public void findOffer_onExistingMoreThanOneAppropriateOffer_shouldGetTheCheapestOne()
    {
        offers.clear();

        Offer firstOffer = new Offer();

        firstOffer.setId("demo1");
        firstOffer.setAgencyCommission(new BigDecimal("4"));
        firstOffer.setApartmentType("single");
        firstOffer.setDepartmentRent(new BigDecimal("34.55"));

        Offer secondOffer = new Offer();

        secondOffer.setId("demo2");
        secondOffer.setAgencyCommission(new BigDecimal("4"));
        secondOffer.setApartmentType("single");
        secondOffer.setDepartmentRent(new BigDecimal("22.21"));

        offers.add(firstOffer);
        offers.add(secondOffer);

        FindOfferModel model = new FindOfferModel();
        model.setApartmentType("single");
        model.setFamilyBudget(new BigDecimal("44.55"));

        OfferServiceModel foundOffer = offerService.findAppropriateOffer(model).orElse(null);

        assert foundOffer != null;
        assertEquals(secondOffer.getId(), foundOffer.getId());
    }

    @Test
    public void findOffer_onNOTFoundAppropriateOffer_shouldReturnNull()
    {
        offers.clear();

        FindOfferModel model = new FindOfferModel();
        model.setApartmentType("family");
        model.setFamilyBudget(new BigDecimal("44.55"));

        Offer offer = new Offer();
        offer.setAgencyCommission(new BigDecimal("3.45"));
        offer.setApartmentType("single");
        offer.setDepartmentRent(new BigDecimal("34.56"));

        offers.add(offer);
        OfferServiceModel found = offerService.findAppropriateOffer(model).orElse(null);

        assertNull(found);
    }

    @Test
    public void allBookedOffers_onGetCall_shouldReturnThemCorrectly()
    {
        offers.clear();

        Offer offer1 = new Offer();
        offer1.setAgencyCommission(new BigDecimal("3.45"));
        offer1.setApartmentType("single");
        offer1.setDepartmentRent(new BigDecimal("34.56"));

        Offer offer2 = new Offer();
        offer2.setAgencyCommission(new BigDecimal("7.65"));
        offer2.setApartmentType("family");
        offer2.setDepartmentRent(new BigDecimal("34.56"));

        offer1.setUser(user);

        offers.add(offer1);
        offers.add(offer2);

        List<OfferServiceModel> booked = offerService.allBookedOffers();
        assertEquals(1, booked.size());
    }

    @Test
    public void allAvailableOffers_onGetCall_shouldReturnThemCorrectly()
    {
        offers.clear();

        Offer offer1 = new Offer();
        offer1.setAgencyCommission(new BigDecimal("3.45"));
        offer1.setApartmentType("single");
        offer1.setDepartmentRent(new BigDecimal("34.56"));

        Offer offer2 = new Offer();
        offer2.setAgencyCommission(new BigDecimal("7.65"));
        offer2.setApartmentType("family");
        offer2.setDepartmentRent(new BigDecimal("34.56"));

        offer1.setUser(user);

        offers.add(offer1);
        offers.add(offer2);

        List<OfferServiceModel> booked = offerService.allAvailableOffers();
        assertEquals(1, booked.size());
    }

}
