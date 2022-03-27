package com.interview.creditcard.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpiryDateValidatorTest {
   private ExpiryDateValidator expiryDateValidator;
    @BeforeEach
    void setUp() {
        expiryDateValidator = new ExpiryDateValidator();
    }

    @Test
    void testIsValidDate() {
        assertTrue(expiryDateValidator.isValid("12/12/2024", null));
    }
    @Test
    void testIsInValidDate() {
        assertFalse(expiryDateValidator.isValid("12/12/2021", null));
    }
    @Test
    void testIsInvalidFormatDate() {
        assertFalse(expiryDateValidator.isValid("12-12-2024", null));
    }
}