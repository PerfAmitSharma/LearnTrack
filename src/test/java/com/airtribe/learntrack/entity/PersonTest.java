package com.airtribe.learntrack.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPersonCreation() {
        Person person = new Person(1, "John", "Doe", "john@example.com");

        assertEquals(1, person.getId());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("john@example.com", person.getEmail());
    }

    @Test
    void testGetDisplayName() {
        Person person = new Person(1, "John", "Doe", "john@example.com");

        assertEquals("John Doe", person.getDisplayName());
    }

    @Test
    void testSetFirstName() {
        Person person = new Person(1, "John", "Doe", "john@example.com");

        person.setFirstName("Jane");

        assertEquals("Jane", person.getFirstName());
    }

    @Test
    void testSetLastName() {
        Person person = new Person(1, "John", "Doe", "john@example.com");

        person.setLastName("Smith");

        assertEquals("Smith", person.getLastName());
    }

    @Test
    void testSetEmail() {
        Person person = new Person(1, "John", "Doe", "john@example.com");

        person.setEmail("jane@example.com");

        assertEquals("jane@example.com", person.getEmail());
    }

    @Test
    void testToString() {
        Person person = new Person(1, "John", "Doe", "john@example.com");

        String expected = "ID: 1, Name: John Doe, Email: john@example.com";
        assertEquals(expected, person.toString());
    }
}

