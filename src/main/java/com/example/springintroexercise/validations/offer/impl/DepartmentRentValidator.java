package com.example.springintroexercise.validations.offer.impl;

import com.example.springintroexercise.validations.offer.DepartmentRentValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class DepartmentRentValidator implements ConstraintValidator<DepartmentRentValidation, BigDecimal>
{
    @Override
    public boolean isValid(BigDecimal rent, ConstraintValidatorContext constraintValidatorContext)
    {
        return rent != null && rent.compareTo(BigDecimal.valueOf(0)) > 0;
    }
}
