package com.bencesoft.validation;

import com.bencesoft.validation.constants.ValidationErrorCodes;
import com.bencesoft.validation.validator.NonNullValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NonNullValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNull {

    String message() default ValidationErrorCodes.NON_NULL;

    Class<?>[] groups() default {};
}
