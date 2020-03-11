package com.example.springintroexercise.data.repositories;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsServiceRepository extends JpaRepository<ClientsService, String>
{
    ClientsService getByDescription(String description);
}
