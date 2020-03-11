package com.example.springintroexercise.data.models.binding.offer;

import com.example.springintroexercise.validations.offer.AgencyCommissionValidation;
import com.example.springintroexercise.validations.offer.ApartmentTypeValidation;
import com.example.springintroexercise.validations.offer.DepartmentRentValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOfferModel
{
    @DepartmentRentValidation
    private BigDecimal departmentRent;

    @ApartmentTypeValidation
    private String apartmentType;

    @AgencyCommissionValidation
    private BigDecimal agencyCommission;
}
