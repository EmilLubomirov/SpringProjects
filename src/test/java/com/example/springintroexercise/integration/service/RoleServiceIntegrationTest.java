package com.example.springintroexercise.integration.service;

import com.example.springintroexercise.data.entities.Role;
import com.example.springintroexercise.data.repositories.RoleRepository;
import com.example.springintroexercise.data.services.RoleService;
import com.example.springintroexercise.integration.TestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleServiceIntegrationTest extends TestBase
{
    @Autowired
    RoleService roleService;

    @MockBean
    RoleRepository roleRepository;

    List<Role> roles;
    Role role;


    @Override
    protected void beforeEach()
    {
        roles = new ArrayList<>();

        role = new Role();
        role.setAuthority("ROOT");

        Mockito.when(roleRepository.findAll()).thenReturn(roles);
    }

    @Test
    public void findAll_onGetCall_shouldReturnThemCorrectly()
    {
        roles.clear();
        roles.add(role);

        assertEquals(1, roleService.getAllRoles().size());
    }

    @Test
    public void findByRoleName_onGetCall_shouldReturnItCorrectly()
    {
        roles.clear();
        roles.add(role);

        Mockito.when(roleRepository.findRoleByAuthority("ROOT"))
                .thenReturn(roles.stream().filter(r -> r.getAuthority().equals("ROOT"))
                        .findFirst()
                        .orElse(null));


        assertEquals(role.getAuthority(), roleService.findByRoleName("ROOT").getAuthority());
    }
}
