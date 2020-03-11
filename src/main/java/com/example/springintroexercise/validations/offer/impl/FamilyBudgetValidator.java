package com.example.springintroexercise.validations.offer.impl;

import com.example.springintroexercise.validations.offer.FamilyBudgetValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class FamilyBudgetValidator implements ConstraintValidator<FamilyBudgetValidation, BigDecimal>
{
    @Override
    public boolean isValid(BigDecimal budget, ConstraintValidatorContext constraintValidatorContext)
    {
        return budget != null && budget.compareTo(BigDecimal.valueOf(0)) > 0;
    }
}
