package org.jhay.common.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LetterSpaceValidator implements ConstraintValidator<ValidName, String> {
    @Override
    public boolean isValid(String  value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }
        return value.matches("[A-Za-z\\s]+");
    }
}
