package com.example.springintroexercise.data.models.service.offer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferServiceModel
{
    private String id;
    private BigDecimal departmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;
    private boolean isBooked;
}
