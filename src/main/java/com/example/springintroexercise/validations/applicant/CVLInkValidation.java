package com.example.springintroexercise.validations.applicant;

import com.example.springintroexercise.validations.applicant.impl.CVLinkValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CVLinkValidator.class)
public @interface CVLInkValidation
{
    String message() default "CV link must NOT be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
