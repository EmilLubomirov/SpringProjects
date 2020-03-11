package com.example.springintroexercise.web.controllers;

import com.example.springintroexercise.api.controllers.RestApiAvailableOffersController;
import com.example.springintroexercise.data.models.binding.offer.FindOfferModel;
import com.example.springintroexercise.data.models.binding.offer.RegisterOfferModel;
import com.example.springintroexercise.data.models.service.offer.OfferServiceModel;
import com.example.springintroexercise.data.services.OfferService;
import com.example.springintroexercise.errors.CustomErrorException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class OffersController
{
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OffersController(OfferService offerService, ModelMapper modelMapper)
    {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("regModel")
    public RegisterOfferModel regModel()
    {
        return new RegisterOfferModel();
    }

    @ModelAttribute("findModel")
    public FindOfferModel findModel(){
        return new FindOfferModel();
    }


    @GetMapping("/offer-reg")
    public ModelAndView showRegData(@ModelAttribute("regModel") RegisterOfferModel model, ModelAndView modelAndView)
    {
        modelAndView.setViewName("offer-register");
        modelAndView.addObject("model", model);
        return modelAndView;
    }

    @PostMapping("/offer-reg")
    public ModelAndView enterRegData(@Valid @ModelAttribute("regModel") RegisterOfferModel model,
                               BindingResult bindingResult) throws Exception
    {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors())
        {
            modelAndView.addObject("model", model);
            modelAndView.setViewName("offer-register");
            return modelAndView;
        }

        offerService.addOffer(modelMapper.map(model, OfferServiceModel.class));
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/offer-find") public String getFindData(@ModelAttribute("findModel") FindOfferModel model)
    {
        return "offer-find";
    }

    @PostMapping("/offer-find")
    public ModelAndView enterFindData(@Valid @ModelAttribute("findModel") FindOfferModel model,
                                      BindingResult result, ModelAndView modelAndView, HttpSession session)
    {
        if (result.hasErrors())
        {
            modelAndView.setViewName("redirect:/offer-find");
            return  modelAndView;
        }

        OfferServiceModel foundOffer = offerService.findAppropriateOffer(model)
                .orElse(null);

        if (foundOffer != null)
        {
            session.setAttribute("foundOffer", foundOffer);
            modelAndView.setViewName("redirect:/offer-found");

            return modelAndView;
        }

        modelAndView.setViewName("redirect:/no-result");
        return modelAndView;
    }

    @GetMapping("/offer-found")
    public ModelAndView getFoundOfferData(HttpSession session)
    {
        ModelAndView modelAndView = new ModelAndView("offer-found");
        OfferServiceModel foundOffer = (OfferServiceModel) session.getAttribute("foundOffer");
        modelAndView.addObject("foundOffer", foundOffer);

        return modelAndView;
    }

    @GetMapping("/no-result")
    public String noOfferFound()
    {
        return "no-offer-found";
    }

    @GetMapping("/bought")
    public String boughtOffer(HttpSession session)
    {
        OfferServiceModel foundOffer = (OfferServiceModel) session.getAttribute("foundOffer");
        offerService.bookOfferForUser(foundOffer.getId(), (String) session.getAttribute("username"));

        session.setAttribute("hasUserOffers", true);
        session.removeAttribute("foundOffer");
        return "redirect:/offers";
    }

    @ExceptionHandler(CustomErrorException.class)
    public ModelAndView noOffersFound(CustomErrorException e)
    {
        ModelAndView modelAndView = new ModelAndView("error-custom");
        modelAndView.addObject("message", e.getMessage());

        return modelAndView;
    }
}
