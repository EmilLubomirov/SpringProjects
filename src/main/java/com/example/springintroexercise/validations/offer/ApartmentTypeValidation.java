package com.example.springintroexercise.validations.offer;

import com.example.springintroexercise.validations.offer.impl.ApartmentTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = ApartmentTypeValidator.class)
public @interface ApartmentTypeValidation
{
    String message() default "Apartment type must not be empty!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
