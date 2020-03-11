package com.example.springintroexercise.data.services.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationUserServiceImpl implements AuthenticationUserService
{

    @Override
    public String getUserUsername()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public List<String> getUserRoles()
    {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
