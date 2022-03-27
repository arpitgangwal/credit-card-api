package com.interview.creditcard.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardNumberValidatorTest {
    private CreditCardNumberValidator creditCardNumberValidator;
    @BeforeEach
    void setUp() {
        creditCardNumberValidator = new CreditCardNumberValidator();
    }

    @Test
    void isValid() {
       assertTrue( creditCardNumberValidator.isValid("5355220907252435",null));
    }
    @Test
    void testIsValidBadCardNumberZero() {
        assertFalse( creditCardNumberValidator.isValid("0",null));
    }
    @Test
    void testIsValidBadCardNumber() {
        assertFalse( creditCardNumberValidator.isValid("12345",null));
    }
}