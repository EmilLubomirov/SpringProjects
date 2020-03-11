package com.example.springintroexercise.data.services.impl;

import com.example.springintroexercise.data.entities.Offer;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.models.binding.offer.FindOfferModel;
import com.example.springintroexercise.data.models.service.offer.OfferServiceModel;
import com.example.springintroexercise.data.repositories.OfferRepository;
import com.example.springintroexercise.data.repositories.UserRepository;
import com.example.springintroexercise.data.services.OfferService;
import com.example.springintroexercise.data.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferServiceImpl implements OfferService
{
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(OfferServiceModel model)
    {
        model.setBooked(false);
        offerRepository.saveAndFlush(modelMapper.map(model, Offer.class));
    }

    @Override
    public List<OfferServiceModel> allBookedOffers()
    {
        return offerRepository.findAll()
                .stream()
                .filter(o -> o.getUser() != null)
                .map(o -> modelMapper.map(o, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferServiceModel> allAvailableOffers()
    {
        return offerRepository.findAll()
                .stream()
                .filter(o -> o.getUser() == null)
                .map(o -> modelMapper.map(o, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OfferServiceModel> findAppropriateOffer(FindOfferModel model)
    {
        List<OfferServiceModel> offers = allAvailableOffers();
        OfferServiceModel found = null;

        BigDecimal priceDifference = new BigDecimal("0");
        BigDecimal minPriceDifference = new BigDecimal("0");

        for (OfferServiceModel offer : offers)
        {
            BigDecimal percent = offer.getAgencyCommission().divide(BigDecimal.valueOf(100.00));
            BigDecimal endPrice = offer.getDepartmentRent().add(offer.getDepartmentRent().multiply(percent));

            if (model.getFamilyBudget().compareTo(endPrice) >= 0 &&
                    model.getApartmentType().equalsIgnoreCase(offer.getApartmentType()))
            {
                priceDifference = endPrice.subtract(model.getFamilyBudget());

                if (found == null)
                {
                    found = offer;
                    minPriceDifference = priceDifference;
                }

                else if (priceDifference.compareTo(minPriceDifference) < 0)
                {
                    found = offer;
                    minPriceDifference = priceDifference;
                }
            }
        }

        return found == null ? Optional.empty() : Optional.of(found);
    }

    @Override
    public void bookOfferForUser(String bookId, String userUsername)
    {
        Offer offer = offerRepository.getOne(bookId);
        User user = userRepository.findByUsername(userUsername);

        user.getOffers().add(offer);
        userRepository.save(user);

        offer.setUser(user);
        offerRepository.save(offer);
    }
}
