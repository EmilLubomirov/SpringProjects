package com.example.springintroexercise.data.repositories;

import com.example.springintroexercise.data.entities.Staff;
import com.example.springintroexercise.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String>
{
    void removeByFirstNameAndLastNameAndPosition(String firstName, String lastName, Position position);
}
