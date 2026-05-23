package com.airtribe.learntrack.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testCourseCreationWithConstructor() {
        Course course = new Course(2001, "Java Basics", "Learn Java fundamentals", 8);

        assertEquals(2001, course.getId());
        assertEquals("Java Basics", course.getCourseName());
        assertEquals("Learn Java fundamentals", course.getDescription());
        assertEquals(8, course.getDurationInWeeks());
        assertTrue(course.isActive());
    }

    @Test
    void testCourseCreationWithNoArgsConstructor() {
        Course course = new Course();

        assertTrue(course.isActive());
    }

    @Test
    void testSetCourseName() {
        Course course = new Course(2001, "Java Basics", "Learn Java fundamentals", 8);

        course.setCourseName("Advanced Java");

        assertEquals("Advanced Java", course.getCourseName());
    }

    @Test
    void testSetDescription() {
        Course course = new Course(2001, "Java Basics", "Learn Java fundamentals", 8);

        course.setDescription("Advanced Java concepts");

        assertEquals("Advanced Java concepts", course.getDescription());
    }

    @Test
    void testSetDurationInWeeks() {
        Course course = new Course(2001, "Java Basics", "Learn Java fundamentals", 8);

        course.setDurationInWeeks(12);

        assertEquals(12, course.getDurationInWeeks());
    }

    @Test
    void testSetActive() {
        Course course = new Course(2001, "Java Basics", "Learn Java fundamentals", 8);
        assertTrue(course.isActive());

        course.setActive(false);

        assertFalse(course.isActive());
    }

    @Test
    void testToString() {
        Course course = new Course(2001, "Java Basics", "Learn Java fundamentals", 8);

        String toString = course.toString();

        assertTrue(toString.contains("ID: 2001"));
        assertTrue(toString.contains("Java Basics"));
        assertTrue(toString.contains("Learn Java fundamentals"));
        assertTrue(toString.contains("8"));
        assertTrue(toString.contains("true"));
    }

    @Test
    void testCourseActiveStatusByDefault() {
        Course course = new Course();
        assertTrue(course.isActive());

        Course course2 = new Course(2001, "Java Basics", "Learn Java fundamentals", 8);
        assertTrue(course2.isActive());
    }
}

