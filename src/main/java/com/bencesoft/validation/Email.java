package com.bencesoft.validation;

import com.bencesoft.validation.constants.ValidationErrorCodes;
import com.bencesoft.validation.validator.EmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default ValidationErrorCodes.EMAIL;

    boolean nullable() default false;

    String allowedSpecialChars() default "._-";

    Class<?>[] groups() default {};
}
