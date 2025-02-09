package com.bencesoft.validation.validator;

import com.bencesoft.validation.NotBlank;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NotBlankValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final NotBlankValidator notBlankValidator = new NotBlankValidator();

    @Test
    public void isValid_ShouldBeValidForNull() {
        // GIVEN
        String value = null;
        notBlankValidator.initialize(getNotBlankAnnotation(true));
        // THEN
        Assertions.assertTrue(notBlankValidator.isValid(value, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeInvalidForNull() {
        // GIVEN
        String value = null;
        notBlankValidator.initialize(getNotBlankAnnotation(false));
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

    private NotBlank getNotBlankAnnotation(boolean isNullable) {
        final NotBlank notBlank = Mockito.mock(NotBlank.class);
        Mockito.when(notBlank.nullable()).thenReturn(isNullable);
        return notBlank;
    }
}
