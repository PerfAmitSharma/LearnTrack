package com.airtribe.learntrack.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testStudentCreationWithEmail() {
        Student student = new Student(1001, "John", "Doe", "john@example.com", "Batch-2024");

        assertEquals(1001, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john@example.com", student.getEmail());
        assertEquals("Batch-2024", student.getBatch());
        assertTrue(student.isActive());
    }

    @Test
    void testStudentCreationWithoutEmail() {
        Student student = new Student(1001, "John", "Doe", "Batch-2024");

        assertEquals(1001, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("", student.getEmail());
        assertEquals("Batch-2024", student.getBatch());
        assertTrue(student.isActive());
    }

    @Test
    void testStudentCreationWithActiveFlag() {
        Student student = new Student(1001, "John", "Doe", "john@example.com", "Batch-2024", false);

        assertEquals(1001, student.getId());
        assertFalse(student.isActive());
    }

    @Test
    void testGetDisplayName() {
        Student student = new Student(1001, "John", "Doe", "john@example.com", "Batch-2024");

        assertEquals("Student: John Doe", student.getDisplayName());
    }

    @Test
    void testSetBatch() {
        Student student = new Student(1001, "John", "Doe", "john@example.com", "Batch-2024");

        student.setBatch("Batch-2025");

        assertEquals("Batch-2025", student.getBatch());
    }

    @Test
    void testSetActive() {
        Student student = new Student(1001, "John", "Doe", "john@example.com", "Batch-2024");
        assertTrue(student.isActive());

        student.setActive(false);

        assertFalse(student.isActive());
    }

    @Test
    void testToString() {
        Student student = new Student(1001, "John", "Doe", "john@example.com", "Batch-2024");

        String toString = student.toString();

        assertTrue(toString.contains("ID: 1001"));
        assertTrue(toString.contains("John Doe"));
        assertTrue(toString.contains("john@example.com"));
        assertTrue(toString.contains("Batch-2024"));
        assertTrue(toString.contains("true"));
    }

    @Test
    void testStudentInheritsFromPerson() {
        Student student = new Student(1001, "John", "Doe", "john@example.com", "Batch-2024");

        assertInstanceOf(Person.class, student);
        assertTrue(student instanceof Person);
    }
}

