package com.example.springintroexercise.data.models.binding.offer;

import com.example.springintroexercise.validations.offer.ApartmentTypeValidation;
import com.example.springintroexercise.validations.offer.FamilyBudgetValidation;
import com.example.springintroexercise.validations.offer.FamilyNameValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindOfferModel
{
    @FamilyBudgetValidation
    private BigDecimal familyBudget;

    @ApartmentTypeValidation
    private String apartmentType;

    @FamilyNameValidation
    private String familyName;
}
