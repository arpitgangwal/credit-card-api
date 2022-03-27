package com.interview.creditcard.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Locale;

public class CardTypeValidator implements ConstraintValidator<CardTypeValidation, String>
{
    @Value("${valid.card.type}")
    String validCardType;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String[] cardTypesArray = validCardType.toLowerCase(Locale.ROOT).split(",");
        return Arrays.asList(cardTypesArray).contains(s.toLowerCase(Locale.ROOT));

    }
}
