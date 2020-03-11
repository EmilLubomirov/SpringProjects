package com.example.springintroexercise.unit;

import com.example.springintroexercise.data.entities.Offer;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.models.service.role.RoleServiceModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import com.example.springintroexercise.data.repositories.UserRepository;
import com.example.springintroexercise.data.services.RoleService;
import com.example.springintroexercise.data.services.UserService;
import com.example.springintroexercise.data.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceUnitTest
{
    UserService userService;

    UserRepository userRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    RoleService roleService;
    List<User> users;
    User user;
    RoleServiceModel userRole;
    RoleServiceModel admin;
    RoleServiceModel root;
    UserServiceModel userServiceModel;

    @BeforeEach
    public void init()
    {
        userServiceModel = new UserServiceModel();
        userServiceModel.setUsername("Ivan");
        users = new ArrayList<>();
        modelMapper = new ModelMapper();

        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        roleService = Mockito.mock(RoleService.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, roleService, modelMapper, passwordEncoder);
        user = new User();
        user.setUsername("Ivan");

//        Mockito.when(userRepository.findByUsername(user.getUsername()))
//                .thenReturn(users.stream().filter(u -> u.getUsername().equals(user.getUsername()))
//                        .findFirst().orElse(null));


        userRole = new RoleServiceModel();
        admin = new RoleServiceModel();
        root = new RoleServiceModel();

        userRole.setAuthority("USER");
        admin.setAuthority("ADMIN");
        root.setAuthority("ROOT");


        Mockito.when(roleService.getAllRoles()).thenReturn(List.of(userRole, admin, root));
        Mockito.when(roleService.findByRoleName("ADMIN")).thenReturn(admin);
        Mockito.when(roleService.findByRoleName("USER")).thenReturn(userRole);
    }

    @Test
    public void register_onEmptyUserRepository_shouldSetUserAll3Roles()
    {
        users.clear();
        Mockito.when(userRepository.count()).thenReturn((long) users.size());
        assertEquals(3, userService.register(userServiceModel).getAuthorities().size());

    }

    @Test
    public void register_on1ExistingUser_shouldSet2Roles()
    {
        users.clear();
        users.add(user);

        Mockito.when(userRepository.count()).thenReturn((long) users.size());
        assertEquals(2, userService.register(userServiceModel).getAuthorities().size());
    }

    @Test
    public void register_on2ExistingUsers_shouldSet1Role()
    {
        users.clear();
        User user2 = new User();

        users.add(user);
        users.add(user2);

        Mockito.when(userRepository.count()).thenReturn((long) users.size());
        assertEquals(1, userService.register(userServiceModel).getAuthorities().size());
    }

    @Test
    public void hasUserOffers_ifUserHaveOffers_shouldReturnTrue()
    {
        users.clear();

        Offer offer = new Offer();
        user.setOffers(Set.of(offer));
        users.add(user);

        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(users.stream().filter(u -> u.getUsername().equals(user.getUsername()))
                        .findFirst().orElse(null));

        assertTrue(userService.hasUserOffers(user.getUsername()));
    }

    @Test
    public void hasUserOffers_ifUserDoesNOTHaveOffers_shouldReturnFalse()
    {
        users.clear();
        users.add(user);

        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(users.stream().filter(u -> u.getUsername().equals(user.getUsername()))
                        .findFirst().orElse(null));

        assertFalse(userService.hasUserOffers(user.getUsername()));
    }

}
