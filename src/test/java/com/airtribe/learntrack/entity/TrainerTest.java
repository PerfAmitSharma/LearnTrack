package com.airtribe.learntrack.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {

    @Test
    void testTrainerCreation() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        assertEquals(4001, trainer.getId());
        assertEquals("Jane", trainer.getFirstName());
        assertEquals("Smith", trainer.getLastName());
        assertEquals("jane@example.com", trainer.getEmail());
        assertEquals("Java", trainer.getSpecialization());
    }

    @Test
    void testGetDisplayName() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        assertEquals("Trainer: Jane Smith", trainer.getDisplayName());
    }

    @Test
    void testSetSpecialization() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        trainer.setSpecialization("Python");

        assertEquals("Python", trainer.getSpecialization());
    }

    @Test
    void testGetSpecialization() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        assertEquals("Java", trainer.getSpecialization());
    }

    @Test
    void testToString() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        String toString = trainer.toString();

        assertTrue(toString.contains("ID: 4001"));
        assertTrue(toString.contains("Trainer: Jane Smith"));
        assertTrue(toString.contains("jane@example.com"));
    }

    @Test
    void testTrainerInheritsFromPerson() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        assertInstanceOf(Person.class, trainer);
        assertTrue(trainer instanceof Person);
    }

    @Test
    void testTrainerSetFirstName() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        trainer.setFirstName("Janet");

        assertEquals("Janet", trainer.getFirstName());
    }

    @Test
    void testTrainerSetEmail() {
        Trainer trainer = new Trainer(4001, "Jane", "Smith", "jane@example.com", "Java");

        trainer.setEmail("janet@example.com");

        assertEquals("janet@example.com", trainer.getEmail());
    }
}

