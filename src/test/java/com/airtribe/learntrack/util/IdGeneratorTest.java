package com.airtribe.learntrack.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    void testGetNextStudentId_ReturnsIncrementingIds() {
        int id1 = IdGenerator.getNextStudentId();
        int id2 = IdGenerator.getNextStudentId();
        int id3 = IdGenerator.getNextStudentId();

        assertTrue(id1 < id2, "ID should increment");
        assertTrue(id2 < id3, "ID should increment");
        assertEquals(1, id2 - id1, "IDs should increment by 1");
        assertEquals(1, id3 - id2, "IDs should increment by 1");
    }

    @Test
    void testGetNextCourseId_ReturnsIncrementingIds() {
        int id1 = IdGenerator.getNextCourseId();
        int id2 = IdGenerator.getNextCourseId();
        int id3 = IdGenerator.getNextCourseId();

        assertTrue(id1 < id2, "ID should increment");
        assertTrue(id2 < id3, "ID should increment");
        assertEquals(1, id2 - id1, "IDs should increment by 1");
        assertEquals(1, id3 - id2, "IDs should increment by 1");
    }

    @Test
    void testGetNextEnrollmentId_ReturnsIncrementingIds() {
        int id1 = IdGenerator.getNextEnrollmentId();
        int id2 = IdGenerator.getNextEnrollmentId();
        int id3 = IdGenerator.getNextEnrollmentId();

        assertTrue(id1 < id2, "ID should increment");
        assertTrue(id2 < id3, "ID should increment");
        assertEquals(1, id2 - id1, "IDs should increment by 1");
        assertEquals(1, id3 - id2, "IDs should increment by 1");
    }

    @Test
    void testGetNextTrainerId_ReturnsIncrementingIds() {
        int id1 = IdGenerator.getNextTrainerId();
        int id2 = IdGenerator.getNextTrainerId();

        assertTrue(id1 < id2, "ID should increment");
        assertEquals(1, id2 - id1, "IDs should increment by 1");
    }

    @Test
    void testEachIdTypeStartsAtExpectedValue() {
        // Get first ID from each type
        int studentId = IdGenerator.getNextStudentId();
        int courseId = IdGenerator.getNextCourseId();
        int enrollmentId = IdGenerator.getNextEnrollmentId();
        int trainerId = IdGenerator.getNextTrainerId();

        // IDs should be > their starting counters (1000, 2000, 3000, 4000)
        assertTrue(studentId > 1000, "Student ID should start from 1001+");
        assertTrue(courseId > 2000, "Course ID should start from 2001+");
        assertTrue(enrollmentId > 3000, "Enrollment ID should start from 3001+");
        assertTrue(trainerId > 4000, "Trainer ID should start from 4001+");
    }
}

