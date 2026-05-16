package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void testRequireNonBlank_WithValidString() {
        assertDoesNotThrow(() -> InputValidator.requireNonBlank("John", "Name"));
    }

    @Test
    void testRequireNonBlank_WithNull() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requireNonBlank(null, "Name"));
        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    void testRequireNonBlank_WithEmptyString() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requireNonBlank("", "Name"));
        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    void testRequireNonBlank_WithWhitespace() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requireNonBlank("   ", "Name"));
        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    void testRequireEmailLike_WithValidEmail() {
        assertDoesNotThrow(() -> InputValidator.requireEmailLike("john@example.com"));
    }

    @Test
    void testRequireEmailLike_WithValidShortEmail() {
        assertDoesNotThrow(() -> InputValidator.requireEmailLike("a@b.c"));
    }

    @Test
    void testRequireEmailLike_WithMissingAt() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requireEmailLike("johnexample.com"));
        assertEquals("Please enter a valid email address.", exception.getMessage());
    }

    @Test
    void testRequireEmailLike_WithTooShort() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requireEmailLike("a@"));
        assertEquals("Please enter a valid email address.", exception.getMessage());
    }

    @Test
    void testRequireEmailLike_WithNull() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requireEmailLike(null));
        assertEquals("Email cannot be empty.", exception.getMessage());
    }

    @Test
    void testRequirePositive_WithPositiveNumber() {
        assertDoesNotThrow(() -> InputValidator.requirePositive(5, "Duration"));
    }

    @Test
    void testRequirePositive_WithZero() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requirePositive(0, "Duration"));
        assertEquals("Duration must be a positive number.", exception.getMessage());
    }

    @Test
    void testRequirePositive_WithNegativeNumber() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> InputValidator.requirePositive(-5, "Duration"));
        assertEquals("Duration must be a positive number.", exception.getMessage());
    }
}

