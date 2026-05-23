package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService();
    }

    @Test
    void testAddCourse_WithValidData() {
        Course course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        assertNotNull(course);
        assertEquals("Java Basics", course.getCourseName());
        assertEquals("Learn Java fundamentals", course.getDescription());
        assertEquals(8, course.getDurationInWeeks());
        assertTrue(course.isActive());
    }

    @Test
    void testAddCourse_WithBlankCourseName() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> courseService.addCourse("", "Learn Java fundamentals", 8));
        assertEquals("Course name cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddCourse_WithBlankDescription() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> courseService.addCourse("Java Basics", "", 8));
        assertEquals("Description cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddCourse_WithNegativeDuration() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> courseService.addCourse("Java Basics", "Learn Java fundamentals", -5));
        assertEquals("Duration in weeks must be a positive number.", exception.getMessage());
    }

    @Test
    void testAddCourse_WithZeroDuration() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> courseService.addCourse("Java Basics", "Learn Java fundamentals", 0));
        assertEquals("Duration in weeks must be a positive number.", exception.getMessage());
    }

    @Test
    void testListCourses_Empty() {
        List<Course> courses = courseService.listCourses();

        assertNotNull(courses);
        assertTrue(courses.isEmpty());
    }

    @Test
    void testListCourses_WithMultipleCourses() {
        courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        courseService.addCourse("Python Advanced", "Learn advanced Python concepts", 10);

        List<Course> courses = courseService.listCourses();

        assertNotNull(courses);
        assertEquals(2, courses.size());
    }

    @Test
    void testListCourses_ReturnsUnmodifiableList() {
        courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        List<Course> courses = courseService.listCourses();

        assertThrows(UnsupportedOperationException.class, () -> courses.add(null));
    }

    @Test
    void testFindCourseById_ExistingCourse() {
        Course added = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        Course found = courseService.findCourseById(added.getId());

        assertNotNull(found);
        assertEquals(added.getId(), found.getId());
        assertEquals("Java Basics", found.getCourseName());
    }

    @Test
    void testFindCourseById_NonExistentCourse() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> courseService.findCourseById(9999));
        assertEquals("Course not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testActivateCourse() {
        Course course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        courseService.deactivateCourse(course.getId());
        assertFalse(course.isActive());

        courseService.activateCourse(course.getId());

        Course activated = courseService.findCourseById(course.getId());
        assertTrue(activated.isActive());
    }

    @Test
    void testActivateCourse_NonExistentCourse() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> courseService.activateCourse(9999));
        assertEquals("Course not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testDeactivateCourse_ExistingCourse() {
        Course course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        assertTrue(course.isActive());

        courseService.deactivateCourse(course.getId());

        Course deactivated = courseService.findCourseById(course.getId());
        assertFalse(deactivated.isActive());
    }

    @Test
    void testDeactivateCourse_NonExistentCourse() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> courseService.deactivateCourse(9999));
        assertEquals("Course not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testHasCourses_Empty() {
        assertFalse(courseService.hasCourses());
    }

    @Test
    void testHasCourses_WithCourses() {
        courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        assertTrue(courseService.hasCourses());
    }

    @Test
    void testCourseUniqueIds() {
        Course c1 = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        Course c2 = courseService.addCourse("Python Advanced", "Learn advanced Python concepts", 10);
        Course c3 = courseService.addCourse("Web Development", "Learn web development", 12);

        assertNotEquals(c1.getId(), c2.getId());
        assertNotEquals(c2.getId(), c3.getId());
        assertNotEquals(c1.getId(), c3.getId());
    }

    @Test
    void testCourseIsActiveDuringCreation() {
        Course course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        assertTrue(course.isActive());
    }
}

