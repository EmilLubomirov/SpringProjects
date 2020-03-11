package com.example.springintroexercise.validations.offer;

import com.example.springintroexercise.validations.offer.impl.DepartmentRentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DepartmentRentValidator.class)
public @interface DepartmentRentValidation
{
    String message() default "Department rent must be greater than 0!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
