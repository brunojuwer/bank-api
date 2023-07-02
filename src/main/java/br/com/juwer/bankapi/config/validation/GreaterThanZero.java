package br.com.juwer.bankapi.config.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = { GreaterThanZeroValidator.class })
public @interface GreaterThanZero {

    String message() default  "Value must be greater than ZERO";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
