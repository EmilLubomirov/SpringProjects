package com.example.springintroexercise.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelServiceDataApiModel
{
    private String description;
    private BigDecimal price;
}
