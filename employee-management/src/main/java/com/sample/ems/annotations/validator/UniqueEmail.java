package com.sample.ems.annotations.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.sample.ems.constants.Constants.EMPLOYEE_COMPANY_EMAIL_FORMAT_VALIDATION_MESSAGE;
import static com.sample.ems.constants.Constants.EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueMailValidator.class)
public @interface UniqueEmail {
    String message() default EMPLOYEE_COMPANY_EMAIL_UNIQUE_VALIDATION_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
