package com.bencesoft.validation.validator;

import com.bencesoft.validation.Username;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private static final int MIN_LENGTH = 5;
    private Username currentAnnotation;

    @Override
    public void initialize(Username constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.currentAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
            return currentAnnotation.nullable();
        }
        return value.matches("^([a-z0-9_.]){" + MIN_LENGTH + ",}$");
    }
}
