package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public final class InputValidator {

    private InputValidator() {
    }

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void requireEmailLike(String email) {
        requireNonBlank(email, "Email");
        if (!email.contains("@") || email.trim().length() < 3) {
            throw new InvalidInputException("Please enter a valid email address.");
        }
    }

    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be a positive number.");
        }
    }
}
