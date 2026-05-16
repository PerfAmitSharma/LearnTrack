package com.airtribe.learntrack.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentStatusTest {

    @Test
    void testEnrollmentStatusValues() {
        assertNotNull(EnrollmentStatus.ACTIVE);
        assertNotNull(EnrollmentStatus.COMPLETED);
        assertNotNull(EnrollmentStatus.CANCELLED);
    }

    @Test
    void testEnrollmentStatusCount() {
        EnrollmentStatus[] values = EnrollmentStatus.values();

        assertEquals(3, values.length);
    }

    @Test
    void testEnrollmentStatusValueOf() {
        assertEquals(EnrollmentStatus.ACTIVE, EnrollmentStatus.valueOf("ACTIVE"));
        assertEquals(EnrollmentStatus.COMPLETED, EnrollmentStatus.valueOf("COMPLETED"));
        assertEquals(EnrollmentStatus.CANCELLED, EnrollmentStatus.valueOf("CANCELLED"));
    }

    @Test
    void testEnrollmentStatusToString() {
        assertEquals("ACTIVE", EnrollmentStatus.ACTIVE.toString());
        assertEquals("COMPLETED", EnrollmentStatus.COMPLETED.toString());
        assertEquals("CANCELLED", EnrollmentStatus.CANCELLED.toString());
    }
}

