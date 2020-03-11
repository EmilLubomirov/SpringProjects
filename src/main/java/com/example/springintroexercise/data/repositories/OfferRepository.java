package com.example.springintroexercise.data.repositories;

import com.example.springintroexercise.data.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String>
{
    void deleteById(String id);
}
