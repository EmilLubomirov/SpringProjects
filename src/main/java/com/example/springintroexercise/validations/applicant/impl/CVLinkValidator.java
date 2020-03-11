package com.example.springintroexercise.validations.applicant.impl;

import com.example.springintroexercise.validations.applicant.CVLInkValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CVLinkValidator implements ConstraintValidator<CVLInkValidation, String>
{

    @Override
    public boolean isValid(String CVLink, ConstraintValidatorContext constraintValidatorContext)
    {
        return !CVLink.isEmpty();
    }
}
