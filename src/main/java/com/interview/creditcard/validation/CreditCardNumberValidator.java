package com.interview.creditcard.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardNumberValidator implements ConstraintValidator<CardNumberValidation, String>
{
    @Override
    public boolean isValid(String creditCardNumber, ConstraintValidatorContext cxt) {
         if( Long.parseLong(creditCardNumber) == 0)
             return false;
            boolean isSecond = false;
            int sum = 0;
            int length = creditCardNumber.length();
            if (length > 19 || length == 0)
                return false;
            for (int i = length - 1; i >=0; i--) {
                int digit = creditCardNumber.charAt(i)- '0';
                if (isSecond) {
                    digit = digit * 2;
                    if (digit > 9) {
                        digit = digit / 10 + digit % 10;
                    }

                }
                isSecond = !isSecond;
                sum = sum + digit;
            }
            return sum % 10 == 0;
        }

}

