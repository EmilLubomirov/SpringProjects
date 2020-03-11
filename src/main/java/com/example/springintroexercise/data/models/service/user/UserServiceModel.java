package com.example.springintroexercise.data.models.service.user;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.entities.Offer;
import com.example.springintroexercise.data.entities.Role;
import com.example.springintroexercise.data.models.service.clientsService.ClientsServiceServiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceModel
{
    private String id;
    private String username;
    private String password;
    private Set<ClientsServiceServiceModel> clientsServices;

    //JUST FOR TESTING
    private Set<Role> authorities;
}
