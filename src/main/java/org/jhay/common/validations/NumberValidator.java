package org.jhay.common.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumberValidator implements ConstraintValidator<ValidNumber, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (!(value instanceof Integer || value instanceof Long ||
                value instanceof Double || value instanceof Float)) {
            return false;
        }

        return String.valueOf(value).matches("-?\\d+(\\.\\d+)?");
    }
}
