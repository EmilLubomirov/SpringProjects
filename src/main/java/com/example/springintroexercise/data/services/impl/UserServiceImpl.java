package com.example.springintroexercise.data.services.impl;

import com.example.springintroexercise.data.entities.Role;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.models.binding.user.UserRegisterModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import com.example.springintroexercise.data.repositories.UserRepository;
import com.example.springintroexercise.data.services.RoleService;
import com.example.springintroexercise.data.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel)
    {
        User user = modelMapper.map(userServiceModel, User.class);

        if (userRepository.count() == 0)
        {
            roleService.seedRolesInDB();

            Set<Role> allRoles = roleService
                    .getAllRoles()
                    .stream()
                    .map(r -> modelMapper.map(r, Role.class))
                    .collect(Collectors.toSet());

            user.setAuthorities(allRoles);
        }

        else if (userRepository.count() == 1)
            {

            Role admin = modelMapper.map(roleService.findByRoleName("ADMIN"), Role.class);
            Role userRole = modelMapper.map(roleService.findByRoleName("USER"), Role.class);
            user.setAuthorities(Set.of(admin, userRole));
        }

        else {

            Role userRole = modelMapper.map(roleService.findByRoleName("USER"), Role.class);
            user.setAuthorities(Set.of(userRole));
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username)
    {
        return modelMapper.map(userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public void update(User user)
    {
        userRepository.save(user);
    }

    @Override
    public boolean hasUserOffers(String username)
    {
        return userRepository.findByUsername(username).getOffers().size() > 0;
    }

    @Override
    public boolean isThereSuchUser(UserRegisterModel model)
    {
        return userRepository.existsByUsername(model.getUsername());
    }

    @Override
    public List<String> allUserUsername()
    {
        return userRepository.findAll()
                .stream()
                .map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public boolean existsByUsername(String username)
    {
        return userRepository.existsByUsername(username);
    }


    @Override
    public boolean existsBysUsernameAndPassword(String username, String password)
    {
        return userRepository.existsByUsernameAndPassword(username, password);
    }
}
