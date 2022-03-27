package com.interview.creditcard.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpiryDateValidator implements ConstraintValidator<ExpirationDateValidation, String>
{
    @Override
    public boolean isValid(String expiryDate, ConstraintValidatorContext constraintValidatorContext) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        sdf.setLenient(false);
        try {
            date=  sdf.parse(expiryDate);
        } catch (ParseException e) {
            return false;
        }
        return  date.after(new Date());
    }
}

