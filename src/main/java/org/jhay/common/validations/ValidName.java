package org.jhay.common.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LetterSpaceValidator.class)
public @interface ValidName {
    String message() default "Must contain only letters and spaces";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
