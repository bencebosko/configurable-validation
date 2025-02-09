package com.bencesoft.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bencesoft.validation.constants.ValidationErrorCodes;
import com.bencesoft.validation.validator.StrongPasswordValidator;
import jakarta.validation.Constraint;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default ValidationErrorCodes.STRONG_PASSWORD;
    boolean nullable() default false;
    Class<?>[] groups() default {};
}
