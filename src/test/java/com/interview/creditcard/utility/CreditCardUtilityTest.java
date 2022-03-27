package com.interview.creditcard.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditCardUtilityTest {

    @Test
    void getMaskedPan() {
        assertEquals( CreditCardUtility.getMaskedPan("abc"),"***");
    }
    @Test
    void testGetMaskedPanEmptyString() {
        assertEquals( CreditCardUtility.getMaskedPan(""),"***");
    }
    @Test
    void testGetMaskedPanNull() {
        assertEquals( CreditCardUtility.getMaskedPan(null),"***");
    }

    @Test
    void getMaskedCreditCardNumber() {
        assertEquals("1****************2", CreditCardUtility.getMaskedCreditCardNumber("1234567891212"));
    }
    @Test
    void testGetMaskedCreditCardNumberWith2String() {
        assertEquals("*******************", CreditCardUtility.getMaskedCreditCardNumber("12"));
    }

    @Test
    void testGetMaskedCreditCardNumberWithEmptyString() {
        assertEquals("*******************", CreditCardUtility.getMaskedCreditCardNumber(""));
    }
    @Test
    void testGetMaskedCreditCardNumberWithNullString() {
        assertEquals("*******************", CreditCardUtility.getMaskedCreditCardNumber(""));
    }

}