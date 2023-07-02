package br.com.juwer.bankapi.config.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class GreaterThanZeroValidator implements ConstraintValidator<GreaterThanZero, Number> {

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;

        if(value != null) {
            var convertedValue = BigDecimal.valueOf(value.doubleValue());
            valid = convertedValue.compareTo(BigDecimal.ZERO) > 0;
        }

        return valid;
    }
}
