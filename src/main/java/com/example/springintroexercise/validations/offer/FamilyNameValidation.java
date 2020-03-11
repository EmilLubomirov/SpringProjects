package com.example.springintroexercise.validations.offer;


import com.example.springintroexercise.validations.offer.impl.FamilyNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = FamilyNameValidator.class)
public @interface FamilyNameValidation
{
    String message() default "Family name must NOT be empty!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
