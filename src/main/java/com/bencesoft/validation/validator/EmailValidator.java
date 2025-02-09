package com.bencesoft.validation.validator;

import com.bencesoft.validation.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private Email currentValidation;

    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.currentValidation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
            return currentValidation.nullable();
        }
        return value.matches("^([a-z0-9_.-])*@([a-z0-9.-]*)\\.([a-z]{2,3})$");
    }
}
