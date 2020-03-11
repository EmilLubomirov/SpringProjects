package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.models.view.OfferViewModel;
import com.example.springintroexercise.data.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestApiAvailableOffersController
{
    private final ModelMapper modelMapper;
    private final OfferService offerService;

    public RestApiAvailableOffersController(ModelMapper modelMapper, OfferService offerService) {
        this.modelMapper = modelMapper;
        this.offerService = offerService;
    }

    @GetMapping("/api-offers")
    public List<OfferViewModel> models(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return offerService.allAvailableOffers()
                .stream()
                .map(o -> modelMapper.map(o, OfferViewModel.class))
                .collect(Collectors.toList());
    }
}
