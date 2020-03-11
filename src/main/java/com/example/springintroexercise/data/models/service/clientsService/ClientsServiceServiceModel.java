package com.example.springintroexercise.data.models.service.clientsService;

import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientsServiceServiceModel
{
    private String id;
    private String description;
    private BigDecimal price;
}
