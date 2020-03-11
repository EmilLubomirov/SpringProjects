package com.example.springintroexercise.web.filters;

import com.example.springintroexercise.data.services.UserService;
import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Component
public class SpentMoneyUserFilter implements Filter {

    private final AuthenticationUserService authenticationUserService;
    private final UserService userService;

    public SpentMoneyUserFilter(AuthenticationUserService authenticationUserService, UserService userService) {
        this.authenticationUserService = authenticationUserService;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        if (authenticationUserService.getUserUsername().equals("anonymousUser"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //request.getSession().setAttribute("additionalSum", userService.getAdditionalSum());
    }
}
