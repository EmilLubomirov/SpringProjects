package com.example.springintroexercise.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such path")
public class CustomErrorException extends RuntimeException
{
    public CustomErrorException(String message) {
        super(message);
    }
}
