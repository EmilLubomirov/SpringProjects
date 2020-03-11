package com.example.springintroexercise.validations.applicant;

import com.example.springintroexercise.validations.applicant.impl.NameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NameValidator.class)
public @interface NameValidation
{
    String message() default "Name must not be empty!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
