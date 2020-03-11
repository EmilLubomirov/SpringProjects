package com.example.springintroexercise.data.services.impl;

import com.example.springintroexercise.data.entities.Role;
import com.example.springintroexercise.data.models.service.role.RoleServiceModel;
import com.example.springintroexercise.data.repositories.RoleRepository;
import com.example.springintroexercise.data.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService
{
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDB()
    {
        roleRepository.saveAndFlush(new Role("ROOT"));
        roleRepository.saveAndFlush(new Role("ADMIN"));
        roleRepository.saveAndFlush(new Role("USER"));
    }

    @Override
    public List<RoleServiceModel> getAllRoles()
    {
        return roleRepository.findAll()
                .stream()
                .map(r -> modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleServiceModel findByRoleName(String roleName)
    {
        return modelMapper.map(roleRepository.findRoleByAuthority(roleName), RoleServiceModel.class);
    }
}
