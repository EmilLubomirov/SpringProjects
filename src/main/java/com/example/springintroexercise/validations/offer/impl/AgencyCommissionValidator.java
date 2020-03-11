package com.example.springintroexercise.validations.offer.impl;

import com.example.springintroexercise.validations.offer.AgencyCommissionValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class AgencyCommissionValidator implements ConstraintValidator<AgencyCommissionValidation, BigDecimal>
{
    @Override
    public boolean isValid(BigDecimal commission, ConstraintValidatorContext constraintValidatorContext)
    {
        return commission != null && commission.compareTo(BigDecimal.valueOf(0)) >= 0;
    }
}
