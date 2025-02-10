package com.bencesoft.validation.validator;

import com.bencesoft.validation.PersonName;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PersonNameValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final PersonNameValidator personNameValidator = new PersonNameValidator();

    @BeforeEach
    public void initMocks() {
        PersonName annotation = Mockito.mock(PersonName.class);
        Mockito.when(annotation.nullable()).thenReturn(false);
        personNameValidator.initialize(annotation);
    }

    @Test
    public void isValid_ShouldBeValidForNull() {
        // GIVEN
        String personName = null;
        PersonName annotation = Mockito.mock(PersonName.class);
        Mockito.when(annotation.nullable()).thenReturn(true);
        personNameValidator.initialize(annotation);
        // THEN
        Assertions.assertTrue(personNameValidator.isValid(personName, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNull() {
        // GIVEN
        String personName = null;
        PersonName annotation = Mockito.mock(PersonName.class);
        Mockito.when(annotation.nullable()).thenReturn(false);
        personNameValidator.initialize(annotation);
        // THEN
        Assertions.assertFalse(personNameValidator.isValid(personName, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForEmptyString() {
        Assertions.assertFalse(personNameValidator.isValid("", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidIfFirstCharIsNotLetter() {
        Assertions.assertFalse(personNameValidator.isValid(".", constraintValidatorContext));
        Assertions.assertFalse(personNameValidator.isValid(" ", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForInvalidChars() {
        Assertions.assertFalse(personNameValidator.isValid("Name!", constraintValidatorContext));
        Assertions.assertFalse(personNameValidator.isValid("Name123", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForLettersWithDot() {
        Assertions.assertTrue(personNameValidator.isValid("K. Péter", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForUnicodeChars() {
        Assertions.assertTrue(personNameValidator.isValid("János", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForWhitespaceChars() {
        Assertions.assertTrue(personNameValidator.isValid("Ákos Mátyás", constraintValidatorContext));
    }
}
