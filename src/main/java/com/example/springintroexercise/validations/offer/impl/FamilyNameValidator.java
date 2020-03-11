package com.example.springintroexercise.validations.offer.impl;

import com.example.springintroexercise.validations.offer.FamilyNameValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FamilyNameValidator implements ConstraintValidator<FamilyNameValidation, String>
{
    @Override
    public boolean isValid(String familyName, ConstraintValidatorContext constraintValidatorContext) {
        return !familyName.isEmpty();
    }
}
