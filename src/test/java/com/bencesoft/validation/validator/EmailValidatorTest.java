package com.bencesoft.validation.validator;

import com.bencesoft.validation.Email;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmailValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final EmailValidator emailValidator = new EmailValidator();

    @Test
    public void isValid_ShouldBeValidForNull() {
        // GIVEN
        String email = null;
        emailValidator.initialize(getEmailAnnotation(true));
        // THEN
        Assertions.assertTrue(emailValidator.isValid(email, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNull() {
        // GIVEN
        String email = null;
        emailValidator.initialize(getEmailAnnotation(false));
        // THEN
        Assertions.assertFalse(emailValidator.isValid(email, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidIfLocalPartInvalid() {
        // GIVEN
        var email = "user!@gmail.com";
        // THEN
        Assertions.assertFalse(emailValidator.isValid(email, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidIfEmailNotContainsAtSign() {
        // GIVEN
        var email = "user.gmail.com";
        // THEN
        Assertions.assertFalse(emailValidator.isValid(email, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidIfDomainInvalid1() {
        // GIVEN
        var email = "user@gmail!.com";
        // THEN
        Assertions.assertFalse(emailValidator.isValid(email, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidIfDomainInvalid2() {
        // GIVEN
        var email = "user@gmail.c";
        // THEN
        Assertions.assertFalse(emailValidator.isValid(email, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidIfDomainInvalid3() {
        // GIVEN
        var email = "user@gmailcom";
        // THEN
        Assertions.assertFalse(emailValidator.isValid(email, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidIfEmailValid() {
        // GIVEN
        var email = "john.doe-123_@gmail-123.sub.com";
        // THEN
        Assertions.assertTrue(emailValidator.isValid(email, constraintValidatorContext));
    }

    private Email getEmailAnnotation(boolean isNullable) {
        final Email email = Mockito.mock(Email.class);
        Mockito.when(email.nullable()).thenReturn(isNullable);
        return email;
    }
}
