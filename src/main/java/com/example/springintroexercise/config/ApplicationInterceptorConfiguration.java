package com.example.springintroexercise.config;

import com.example.springintroexercise.web.interceptors.HasUserAppliedForAJobInterceptor;
import com.example.springintroexercise.web.interceptors.HasUserOffersInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationInterceptorConfiguration implements WebMvcConfigurer
{
    private final HasUserOffersInterceptor hasUserOffersInterceptor;
    private final HasUserAppliedForAJobInterceptor hasUserAppliedForAJobInterceptor;

    public ApplicationInterceptorConfiguration(HasUserOffersInterceptor hasUserOffersInterceptor, HasUserAppliedForAJobInterceptor hasUserAppliedForAJobInterceptor) {
        this.hasUserOffersInterceptor = hasUserOffersInterceptor;
        this.hasUserAppliedForAJobInterceptor = hasUserAppliedForAJobInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(hasUserOffersInterceptor);
        registry.addInterceptor(hasUserAppliedForAJobInterceptor);
    }
}
