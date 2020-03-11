package com.example.springintroexercise.validations.offer.impl;

import com.example.springintroexercise.validations.offer.ApartmentTypeValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ApartmentTypeValidator implements ConstraintValidator<ApartmentTypeValidation, String>
{

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext)
    {
        return !type.isBlank();
    }
}
