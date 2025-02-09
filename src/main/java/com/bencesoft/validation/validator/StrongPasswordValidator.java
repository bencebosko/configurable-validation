package com.bencesoft.validation.validator;

import com.bencesoft.validation.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    private static final int MIN_LENGTH = 8;
    private StrongPassword currentAnnotation;

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        currentAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(password)) {
            return currentAnnotation.nullable();
        }
        if (password.length() < MIN_LENGTH) {
            return false;
        }
        var hasUppercase = false;
        var hasLowercase = false;
        var hasDigit = false;
        var hasSpecial = false;
        for (int idx = 0; idx < password.length() ; idx++) {
            final var ch = password.charAt(idx);
            if (Character.isWhitespace(ch)) {
                return false;
            } else if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
            if (hasUppercase && hasLowercase && hasDigit && hasSpecial) {
                return true;
            }
        }
        return false;
    }
}
