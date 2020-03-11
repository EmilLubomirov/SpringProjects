package com.example.springintroexercise.data.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferViewModel
{
    private BigDecimal departmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;
}
