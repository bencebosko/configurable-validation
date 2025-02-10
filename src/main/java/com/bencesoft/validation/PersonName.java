package com.bencesoft.validation;

import com.bencesoft.validation.constants.ValidationErrorCodes;
import com.bencesoft.validation.validator.PersonNameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PersonNameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonName {

    String message() default ValidationErrorCodes.PERSON_NAME;

    boolean nullable() default false;

    Class<?>[] groups() default {};
}
