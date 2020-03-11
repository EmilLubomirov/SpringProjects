package com.example.springintroexercise.data.models.service.applicant;

import com.example.springintroexercise.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantServiceModel
{
    private String id;
    private String firstName;
    private String lastName;
    private String linkToCV;
    private String phoneNumber;
    private Position requestedPosition;
    private String username;
}
