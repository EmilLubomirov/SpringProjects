package com.example.springintroexercise.unit;

import com.example.springintroexercise.data.entities.Role;
import com.example.springintroexercise.data.repositories.RoleRepository;
import com.example.springintroexercise.data.services.RoleService;
import com.example.springintroexercise.data.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleServiceUnitTest
{
    RoleService roleService;

    RoleRepository roleRepository;
    ModelMapper modelMapper;
    List<Role> roles;
    Role role;


    @BeforeEach
    public void init()
    {
        roles = new ArrayList<>();

        role = new Role();
        role.setAuthority("ROOT");

        roleRepository = Mockito.mock(RoleRepository.class);
        modelMapper = new ModelMapper();

        roleService = new RoleServiceImpl(roleRepository, modelMapper);
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

        Mockito.when(roleRepository.findRoleByAuthority(role.getAuthority()))
                .thenReturn(roles.stream().filter(r -> r.getAuthority()
                        .equals(role.getAuthority())).findFirst().orElse(null));


        assertEquals(role.getAuthority(), roleService.findByRoleName(role.getAuthority())
                .getAuthority());
    }
}
