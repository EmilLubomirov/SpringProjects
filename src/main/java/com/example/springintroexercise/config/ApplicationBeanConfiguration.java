package com.example.springintroexercise.config;

import com.example.springintroexercise.utils.staff.StaffUtil;
import com.example.springintroexercise.utils.staff.StaffUtilImpl;
import com.example.springintroexercise.utils.user.validator.RegisterUserUtil;
import com.example.springintroexercise.utils.user.validator.RegisterUserUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationBeanConfiguration
{
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    public StaffUtil staffUtil(){return new StaffUtilImpl();}

    @Bean
    public RegisterUserUtil registerUserUtil() {return new RegisterUserUtilImpl();}
}
