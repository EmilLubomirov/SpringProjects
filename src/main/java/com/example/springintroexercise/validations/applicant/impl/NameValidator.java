package com.example.springintroexercise.validations.applicant.impl;

import com.example.springintroexercise.validations.applicant.NameValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameValidation, String>
{
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !name.isEmpty();
    }
}
