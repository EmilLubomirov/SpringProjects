package com.example.springintroexercise.data.entities;

import com.example.springintroexercise.data.entities.person.PersonEntity;
import com.example.springintroexercise.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends PersonEntity
{

    @Enumerated(EnumType.STRING)
    private Position position;
}
