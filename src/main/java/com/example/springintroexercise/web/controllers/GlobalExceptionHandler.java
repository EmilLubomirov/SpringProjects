package com.example.springintroexercise.web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler({Throwable.class})
    public ModelAndView error(Throwable e)
    {
        return new ModelAndView("error");
    }
}
