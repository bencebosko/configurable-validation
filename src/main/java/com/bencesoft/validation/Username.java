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
public @interface Username {

    String message() default ValidationErrorCodes.USERNAME;

    boolean nullable() default false;

    int minLength() default 5;

    String allowedSpecialChars() default "_.";

    Class<?>[] groups() default {};
}
