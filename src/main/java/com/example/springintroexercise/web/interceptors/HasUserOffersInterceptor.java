package com.example.springintroexercise.web.interceptors;

import com.example.springintroexercise.data.services.UserService;
import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HasUserOffersInterceptor implements HandlerInterceptor
{
    private final UserService userService;
    private final AuthenticationUserService authenticationUserService;

    public HasUserOffersInterceptor(UserService userService, AuthenticationUserService authenticationUserService) {
        this.userService = userService;
        this.authenticationUserService = authenticationUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String userUsername = authenticationUserService.getUserUsername();

        if (!userUsername.equals("anonymousUser"))
        {
            request.getSession().setAttribute("hasUserOffers",
                    userService.hasUserOffers(userUsername));
        }

        return true;
    }

}
