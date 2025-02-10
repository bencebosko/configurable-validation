package com.bencesoft.validation.validator;

import com.bencesoft.validation.NotBlank;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NotBlankValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final NotBlankValidator notBlankValidator = new NotBlankValidator();

    @BeforeEach
    public void initMocks() {
        NotBlank annotation = Mockito.mock(NotBlank.class);
        Mockito.when(annotation.nullable()).thenReturn(false);
        notBlankValidator.initialize(annotation);
    }

    @Test
    public void isValid_ShouldBeValidForNull() {
        // GIVEN
        String value = null;
        NotBlank annotation = Mockito.mock(NotBlank.class);
        Mockito.when(annotation.nullable()).thenReturn(true);
        notBlankValidator.initialize(annotation);
        // THEN
        Assertions.assertTrue(notBlankValidator.isValid(value, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNull() {
        // GIVEN
        String value = null;
        // THEN
        Assertions.assertFalse(notBlankValidator.isValid(value, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForEmptyString() {
        Assertions.assertFalse(notBlankValidator.isValid("", constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForNonEmptyString() {
        Assertions.assertTrue(notBlankValidator.isValid("nonEmpty", constraintValidatorContext));
    }
}
