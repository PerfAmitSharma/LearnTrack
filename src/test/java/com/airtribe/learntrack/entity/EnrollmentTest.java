package com.airtribe.learntrack.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentTest {

    @Test
    void testEnrollmentCreationWithNoArgsConstructor() {
        Enrollment enrollment = new Enrollment();

        assertNotNull(enrollment);
    }

    @Test
    void testEnrollmentCreationWithAllParameters() {
        LocalDate date = LocalDate.now();
        Enrollment enrollment = new Enrollment(3001, 1001, 2001, date, EnrollmentStatus.ACTIVE);

        assertEquals(3001, enrollment.getId());
        assertEquals(1001, enrollment.getStudentId());
        assertEquals(2001, enrollment.getCourseId());
        assertEquals(date, enrollment.getEnrollmentDate());
        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());
    }

    @Test
    void testSetId() {
        Enrollment enrollment = new Enrollment();

        enrollment.setId(3001);

        assertEquals(3001, enrollment.getId());
    }

    @Test
    void testSetStudentId() {
        Enrollment enrollment = new Enrollment();

        enrollment.setStudentId(1001);

        assertEquals(1001, enrollment.getStudentId());
    }

    @Test
    void testSetCourseId() {
        Enrollment enrollment = new Enrollment();

        enrollment.setCourseId(2001);

        assertEquals(2001, enrollment.getCourseId());
    }

    @Test
    void testSetEnrollmentDate() {
        Enrollment enrollment = new Enrollment();
        LocalDate date = LocalDate.now();

        enrollment.setEnrollmentDate(date);

        assertEquals(date, enrollment.getEnrollmentDate());
    }

    @Test
    void testSetStatus() {
        Enrollment enrollment = new Enrollment();

        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());

        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        assertEquals(EnrollmentStatus.COMPLETED, enrollment.getStatus());

        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        assertEquals(EnrollmentStatus.CANCELLED, enrollment.getStatus());
    }

    @Test
    void testToString() {
        LocalDate date = LocalDate.now();
        Enrollment enrollment = new Enrollment(3001, 1001, 2001, date, EnrollmentStatus.ACTIVE);

        String toString = enrollment.toString();

        assertTrue(toString.contains("ID: 3001"));
        assertTrue(toString.contains("Student ID: 1001"));
        assertTrue(toString.contains("Course ID: 2001"));
        assertTrue(toString.contains(date.toString()));
        assertTrue(toString.contains("ACTIVE"));
    }
}

