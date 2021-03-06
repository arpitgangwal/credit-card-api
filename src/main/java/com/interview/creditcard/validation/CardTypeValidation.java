package com.interview.creditcard.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CardTypeValidator.class)
public @interface CardTypeValidation {
    //error message
     String message() default "Invalid credit card number";
    //represents group of constraints
     Class<?>[] groups() default {};
    //represents additional information about annotation
     Class<? extends Payload>[] payload() default {};
}

