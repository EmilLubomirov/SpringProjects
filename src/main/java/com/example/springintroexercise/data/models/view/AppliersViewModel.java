package com.example.springintroexercise.data.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliersViewModel
{
    private String firstName;
    private String lastName;
    private String linkToCV;
    private String phoneNumber;
    private String requestedPosition;
    private String username;
}
