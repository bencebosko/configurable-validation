package com.bencesoft.validation.validator;

import com.bencesoft.validation.NonNegative;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NonNegativeValidatorTest {

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
    private final NonNegativeValidator nonNegativeValidator = new NonNegativeValidator();

    @Test
    public void issValid_ShouldBeValidForNull() {
        // GIVEN
        Double value = null;
        nonNegativeValidator.initialize(getNonNegativeAnnotation(true));
        // THEN
        Assertions.assertTrue(nonNegativeValidator.isValid(value, constraintValidatorContext));
    }

    @Test
    public void issValid_ShouldBeInvalidForNull() {
        // GIVEN
        Double value = null;
        nonNegativeValidator.initialize(getNonNegativeAnnotation(false));
        // THEN
        Assertions.assertFalse(nonNegativeValidator.isValid(value, constraintValidatorContext));
    }


    @Test
    public void isValid_ShouldBeInvalidForNegativeNumbers() {
        Assertions.assertFalse(nonNegativeValidator.isValid(-1 * Double.MAX_VALUE, constraintValidatorContext));
        Assertions.assertFalse(nonNegativeValidator.isValid(-1 * Long.MAX_VALUE, constraintValidatorContext));
        Assertions.assertFalse(nonNegativeValidator.isValid(-0.0001, constraintValidatorContext));
        Assertions.assertFalse(nonNegativeValidator.isValid(-1, constraintValidatorContext));
    }

    @Test
    public void isValid_ShouldBeValidForNonNegativeNumbers() {
        Assertions.assertTrue(nonNegativeValidator.isValid(Double.MAX_VALUE, constraintValidatorContext));
        Assertions.assertTrue(nonNegativeValidator.isValid(Long.MAX_VALUE, constraintValidatorContext));
        Assertions.assertTrue(nonNegativeValidator.isValid(0.0001, constraintValidatorContext));
        Assertions.assertTrue(nonNegativeValidator.isValid(0, constraintValidatorContext));
        Assertions.assertTrue(nonNegativeValidator.isValid(-0, constraintValidatorContext));
    }

    private NonNegative getNonNegativeAnnotation(boolean isNullable) {
        final NonNegative personName = Mockito.mock(NonNegative.class);
        Mockito.when(personName.nullable()).thenReturn(isNullable);
        return personName;
    }
}
