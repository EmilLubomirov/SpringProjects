package com.example.springintroexercise.data.models.service.staff;

import com.example.springintroexercise.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffServiceModel
{
    private String id;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position;
}
