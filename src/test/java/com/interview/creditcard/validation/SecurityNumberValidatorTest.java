package com.interview.creditcard.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityNumberValidatorTest {

    private SecurityNumberValidator securityNumberValidator;
    @BeforeEach
    void setUp() {
        securityNumberValidator = new SecurityNumberValidator();
    }

    @Test
    void testIsValidDate() {
        assertTrue(securityNumberValidator.isValid("123", null));
    }
    @Test
    void testIsInValidDate() {
        assertFalse(securityNumberValidator.isValid("12", null));
    }
    @Test
    void testIsInvalidFormatDate() {
        assertFalse(securityNumberValidator.isValid("abc", null));
    }
}