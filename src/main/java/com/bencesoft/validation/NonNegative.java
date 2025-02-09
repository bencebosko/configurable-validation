package com.bencesoft.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bencesoft.validation.constants.ValidationErrorCodes;
import com.bencesoft.validation.validator.NonNegativeValidator;
import jakarta.validation.Constraint;

@Documented
@Constraint(validatedBy = NonNegativeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNegative {
    String message() default ValidationErrorCodes.NON_NEGATIVE;
    boolean nullable() default false;
    Class<?>[] groups() default {};
}
