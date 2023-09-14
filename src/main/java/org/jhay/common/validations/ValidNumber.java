package org.jhay.common.validations;

;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberValidator.class)
public @interface ValidNumber {

    String message() default "Person Id must be a number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

