package com.airtribe.learntrack.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidInputExceptionTest {

    @Test
    void testInvalidInputException_WithMessage() {
        String message = "Email cannot be empty.";

        InvalidInputException exception = new InvalidInputException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testInvalidInputException_IsRuntimeException() {
        InvalidInputException exception = new InvalidInputException("Test message");

        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testInvalidInputException_CanBeCaught() {
        assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException("Test");
        });
    }
}

