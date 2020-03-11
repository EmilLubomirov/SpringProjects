package com.example.springintroexercise.data.repositories;

import com.example.springintroexercise.data.entities.Applicant;
import com.example.springintroexercise.data.models.view.AppliersViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String>
{
    boolean existsByUsername(String username);

    Applicant findByUsername(String username);

    void deleteByUsername(String username);
}
