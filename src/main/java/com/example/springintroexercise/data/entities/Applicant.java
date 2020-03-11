package com.example.springintroexercise.data.entities;

import com.example.springintroexercise.data.entities.person.PersonEntity;
import com.example.springintroexercise.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "applicants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Applicant extends PersonEntity
{
    @Column(name = "link_to_CV")
    private String linkToCV;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "requested_position")
    @Enumerated(EnumType.STRING)
    private Position requestedPosition;

    private String username;
}
