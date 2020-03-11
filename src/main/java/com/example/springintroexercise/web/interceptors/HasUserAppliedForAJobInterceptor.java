package com.example.springintroexercise.web.interceptors;

import com.example.springintroexercise.data.services.ApplicantService;
import com.example.springintroexercise.data.services.StaffService;
import com.example.springintroexercise.data.services.auth.AuthenticationUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HasUserAppliedForAJobInterceptor implements HandlerInterceptor
{
    private final ApplicantService applicantService;
    private final AuthenticationUserService authenticationUserService;


    public HasUserAppliedForAJobInterceptor(ApplicantService applicantService, AuthenticationUserService authenticationUserService) {
        this.applicantService = applicantService;
        this.authenticationUserService = authenticationUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String userUsername = authenticationUserService.getUserUsername();

        if (!userUsername.equals("anonymousUser"))
        {
            request.getSession().setAttribute("hasApplied",
                    applicantService.existsByUsername(userUsername));
        }

        return true;
    }
}
