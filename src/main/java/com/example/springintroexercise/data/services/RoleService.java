package com.example.springintroexercise.data.services;

import com.example.springintroexercise.data.models.service.role.RoleServiceModel;

import java.util.List;

public interface RoleService
{
    void seedRolesInDB();

    List<RoleServiceModel> getAllRoles();

    RoleServiceModel findByRoleName(String roleName);
}
