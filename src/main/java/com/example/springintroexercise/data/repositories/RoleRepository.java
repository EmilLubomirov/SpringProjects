package com.example.springintroexercise.data.repositories;

import com.example.springintroexercise.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>
{
    Role findRoleByAuthority(String authority);
}
