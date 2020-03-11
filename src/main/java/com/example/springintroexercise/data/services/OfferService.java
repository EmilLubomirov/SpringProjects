package com.example.springintroexercise.data.services;

import com.example.springintroexercise.data.models.binding.offer.FindOfferModel;
import com.example.springintroexercise.data.models.service.offer.OfferServiceModel;

import java.util.List;
import java.util.Optional;

public interface OfferService
{
    void addOffer(OfferServiceModel model) throws Exception;

    List<OfferServiceModel> allBookedOffers();

    List<OfferServiceModel> allAvailableOffers();

   Optional<OfferServiceModel> findAppropriateOffer(FindOfferModel model);

    void bookOfferForUser(String offerId, String userUsername);
}
