package com.bencesoft.validation;

import com.bencesoft.validation.constants.ValidationErrorCodes;
import com.bencesoft.validation.validator.NotBlankValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NotBlankValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {
    String message() default ValidationErrorCodes.NOT_BLANK;
    boolean nullable() default false;
    Class<?>[] groups() default {};
}
