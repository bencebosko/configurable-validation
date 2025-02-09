package com.bencesoft.validation.validator;

import com.bencesoft.validation.StrongPassword;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StrongPasswordValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final StrongPasswordValidator strongPasswordValidator = new StrongPasswordValidator();

    @Test
    public void isValid_ShouldBeValidForNull() {
        // GIVEN
        String password = null;
        strongPasswordValidator.initialize(getStrongPasswordAnnotation(true));
        // THEN
        Assertions.assertTrue(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNull() {
        // GIVEN
        String password = null;
        strongPasswordValidator.initialize(getStrongPasswordAnnotation(false));
        // THEN
        Assertions.assertFalse(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForShortPassword() {
        var password = "John12.";
        Assertions.assertFalse(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNoUppercase() {
        var password = "johndoe123.";
        Assertions.assertFalse(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNoLowercase() {
        var password = "JOHNDOE123.";
        Assertions.assertFalse(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNoDigit() {
        var password = "JohnDoeNoDigit.";
        Assertions.assertFalse(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNoSpecial() {
        var password = "JohnDoe123";
        Assertions.assertFalse(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForWhitespace() {
        var password = "JohnDoe 123.";
        Assertions.assertFalse(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValid() {
        var password = "JohnDoe123.";
        Assertions.assertTrue(strongPasswordValidator.isValid(password, constraintValidatorContext));
    }

    private StrongPassword getStrongPasswordAnnotation(boolean isNullable) {
        final StrongPassword strongPassword = Mockito.mock(StrongPassword.class);
        Mockito.when(strongPassword.nullable()).thenReturn(isNullable);
        return strongPassword;
    }
}
