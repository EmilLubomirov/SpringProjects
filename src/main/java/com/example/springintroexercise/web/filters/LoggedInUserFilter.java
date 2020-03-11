package com.example.springintroexercise.web.filters;

import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LoggedInUserFilter implements Filter
{
    private final AuthenticationUserService authenticationUserService;

    public LoggedInUserFilter(AuthenticationUserService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String username = authenticationUserService.getUserUsername();

        if (username.equals("anonymousUser"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("roles", authenticationUserService.getUserRoles());

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
