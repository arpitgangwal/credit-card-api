package com.interview.creditcard.utility;

import com.interview.creditcard.entity.CreditCardEntity;
import com.interview.creditcard.model.CreditCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreditCardUtility {

    public static String getMaskedPan(String pan) {
        return "***";
    }

    public static String getMaskedCreditCardNumber(String pan) {
       String masked="*******************";
       if(!pan.isEmpty() && pan.length()>2)
         return pan.charAt(0)+masked.substring(1,masked.length()-2)+pan.charAt(pan.length()-1);
       else
           return masked;
    }

    public static CreditCardEntity mapCreditCardObject(CreditCard creditCard) throws ParseException {
       return new CreditCardEntity(Long.parseLong(creditCard.getCardNumber()),creditCard.getUsername(),creditCard.getCardType(),new SimpleDateFormat("MM/dd/yyyy").parse(creditCard.getExpirationDate()),creditCard.getSecurityCode(),creditCard.getBankName(),0.00);
    }
}
