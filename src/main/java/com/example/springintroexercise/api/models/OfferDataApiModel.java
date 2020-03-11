package com.example.springintroexercise.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDataApiModel
{
    private BigDecimal departmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;
}
