package com.example.springintroexercise.data.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientsServiceViewModel
{
    private String description;
    private BigDecimal price;
}
