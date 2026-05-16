package com.airtribe.learntrack.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityNotFoundExceptionTest {

    @Test
    void testEntityNotFoundException_WithMessage() {
        String message = "Student not found with ID: 1001";

        EntityNotFoundException exception = new EntityNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testEntityNotFoundException_IsRuntimeException() {
        EntityNotFoundException exception = new EntityNotFoundException("Test message");

        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testEntityNotFoundException_CanBeCaught() {
        assertThrows(EntityNotFoundException.class, () -> {
            throw new EntityNotFoundException("Test");
        });
    }
}

