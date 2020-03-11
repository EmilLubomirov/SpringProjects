package com.example.springintroexercise.validations.applicant;

import com.example.springintroexercise.validations.applicant.impl.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberValidation
{
    String message() default "Phone number must NOT be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
