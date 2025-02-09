package com.bencesoft.validation.validator;

import com.bencesoft.validation.Username;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsernameValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final UsernameValidator usernameValidator = new UsernameValidator();

    @Test
    public void isValid_ShouldBeValidForNull() {
        // GIVEN
        String userName = null;
        usernameValidator.initialize(getUsernameAnnotation(true));
        // THEN
        Assertions.assertTrue(usernameValidator.isValid(userName, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNull() {
        // GIVEN
        String userName = null;
        usernameValidator.initialize(getUsernameAnnotation(false));
        // THEN
        Assertions.assertFalse(usernameValidator.isValid(userName, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForShortNames() {
        Assertions.assertFalse(usernameValidator.isValid("john", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForInvalidChars() {
        Assertions.assertFalse(usernameValidator.isValid("john.doe#", constraintValidatorContext));
        Assertions.assertFalse(usernameValidator.isValid("John.doe", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForValidUsername() {
        Assertions.assertTrue(usernameValidator.isValid("john.doe_1", constraintValidatorContext));
    }

    private Username getUsernameAnnotation(boolean isNullable) {
        final Username username = Mockito.mock(Username.class);
        Mockito.when(username.nullable()).thenReturn(isNullable);
        return username;
    }
}
