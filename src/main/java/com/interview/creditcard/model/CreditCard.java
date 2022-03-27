package com.interview.creditcard.model;

import com.interview.creditcard.validation.CardNumberValidation;
import com.interview.creditcard.validation.CardTypeValidation;
import com.interview.creditcard.validation.ExpirationDateValidation;
import com.interview.creditcard.validation.SecurityCodeValidator;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class CreditCard {
    @NotNull
    @CardNumberValidation
    private String cardNumber;
    @NotNull
    private String username;
    @NotNull
    @CardTypeValidation
    private String cardType;
    @NotNull
    @ExpirationDateValidation
    private String expirationDate;
    @NotNull
    @SecurityCodeValidator
    private String securityCode;
    @NotNull
    private String bankName;

}
