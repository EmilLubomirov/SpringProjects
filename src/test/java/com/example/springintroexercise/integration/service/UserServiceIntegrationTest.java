package com.example.springintroexercise.integration.service;

import com.example.springintroexercise.data.entities.Offer;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.models.service.role.RoleServiceModel;
import com.example.springintroexercise.data.models.service.user.UserServiceModel;
import com.example.springintroexercise.data.repositories.UserRepository;
import com.example.springintroexercise.data.services.RoleService;
import com.example.springintroexercise.data.services.UserService;
import com.example.springintroexercise.data.services.impl.UserServiceImpl;
import com.example.springintroexercise.integration.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceIntegrationTest extends TestBase
{
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    RoleService roleService;

    List<User> users;
    User user;
    RoleServiceModel userRole;
    RoleServiceModel admin;
    RoleServiceModel root;
    UserServiceModel userServiceModel;


    @Override
    protected void beforeEach()
    {
        userServiceModel = new UserServiceModel();
        userServiceModel.setAuthorities(new HashSet<>());

        userServiceModel.setUsername("Ivan");
        users = new ArrayList<>();

        user = new User();

        userRole = new RoleServiceModel();
        admin = new RoleServiceModel();
        root = new RoleServiceModel();

        userRole.setAuthority("USER");
        admin.setAuthority("ADMIN");
        root.setAuthority("ROOT");

        Mockito.when(userRepository.findByUsername("Ivan")).thenReturn(user);

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
        Offer offer = new Offer();
        user.setOffers(Set.of(offer));

        assertTrue(userService.hasUserOffers("Ivan"));
    }

    @Test
    public void hasUserOffers_ifUserDoesNOTHaveOffers_shouldReturnFalse()
    {
        assertFalse(userService.hasUserOffers("Ivan"));
    }
}
