package com.example.springintroexercise.validations.applicant.impl;

import com.example.springintroexercise.validations.applicant.PhoneNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String>
{
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext)
    {
        return !phoneNumber.isEmpty();
    }
}
