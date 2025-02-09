package com.bencesoft.validation.validator;

import com.bencesoft.validation.PersonName;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PersonNameValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final PersonNameValidator personNameValidator = new PersonNameValidator();

    @Test
    public void isValid_ShouldBeValidForNull() {
        // GIVEN
        String personName = null;
        personNameValidator.initialize(getPersonNameAnnotation(true));
        // THEN
        Assertions.assertTrue(personNameValidator.isValid(personName, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNull() {
        // GIVEN
        String personName = null;
        personNameValidator.initialize(getPersonNameAnnotation(false));
        // THEN
        Assertions.assertFalse(personNameValidator.isValid(personName, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForEmptyString() {
        Assertions.assertFalse(personNameValidator.isValid("", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNonLetterChars() {
        Assertions.assertFalse(personNameValidator.isValid("Name.", constraintValidatorContext));
        Assertions.assertFalse(personNameValidator.isValid("Name!", constraintValidatorContext));
        Assertions.assertFalse(personNameValidator.isValid("Name123", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForUnicodeChars() {
        Assertions.assertTrue(personNameValidator.isValid("János", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForWhitespaceChars() {
        Assertions.assertTrue(personNameValidator.isValid("Ákos Mátyás", constraintValidatorContext));
    }

    private PersonName getPersonNameAnnotation(boolean isNullable) {
        final PersonName personName = Mockito.mock(PersonName.class);
        Mockito.when(personName.nullable()).thenReturn(isNullable);
        return personName;
    }
}
