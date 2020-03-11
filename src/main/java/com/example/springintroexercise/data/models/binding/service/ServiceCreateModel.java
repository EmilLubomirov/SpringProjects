package com.example.springintroexercise.data.models.binding.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCreateModel
{
    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;
}
