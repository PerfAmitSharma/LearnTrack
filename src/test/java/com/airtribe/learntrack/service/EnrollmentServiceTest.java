package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceTest {

    private EnrollmentService enrollmentService;
    private StudentService studentService;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService(studentService, courseService);
    }

    @Test
    void testEnrollStudentInCourse_Success() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        Enrollment enrollment = enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        assertNotNull(enrollment);
        assertEquals(student.getId(), enrollment.getStudentId());
        assertEquals(course.getId(), enrollment.getCourseId());
        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());
        assertNotNull(enrollment.getEnrollmentDate());
    }

    @Test
    void testEnrollStudentInCourse_StudentNotFound() {
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> enrollmentService.enrollStudentInCourse(9999, course.getId()));
        assertEquals("Student not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourse_CourseNotFound() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> enrollmentService.enrollStudentInCourse(student.getId(), 9999));
        assertEquals("Course not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourse_InactiveStudent() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        studentService.deactivateStudent(student.getId());

        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> enrollmentService.enrollStudentInCourse(student.getId(), course.getId()));
        assertEquals("Cannot enroll an inactive student.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourse_InactiveCourse() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        courseService.deactivateCourse(course.getId());

        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> enrollmentService.enrollStudentInCourse(student.getId(), course.getId()));
        assertEquals("Cannot enroll in an inactive course.", exception.getMessage());
    }

    @Test
    void testEnrollStudentInCourse_DuplicateActiveEnrollment() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> enrollmentService.enrollStudentInCourse(student.getId(), course.getId()));
        assertEquals("Student is already actively enrolled in this course.", exception.getMessage());
    }

    @Test
    void testEnrollMultipleTimes_AfterCancellation() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        Enrollment enrollment1 = enrollmentService.enrollStudentInCourse(student.getId(), course.getId());
        enrollmentService.cancelEnrollment(enrollment1.getId());

        Enrollment enrollment2 = enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        assertNotNull(enrollment2);
        assertEquals(EnrollmentStatus.ACTIVE, enrollment2.getStatus());
        assertNotEquals(enrollment1.getId(), enrollment2.getId());
    }

    @Test
    void testListEnrollments_Empty() {
        List<Enrollment> enrollments = enrollmentService.listEnrollments();

        assertNotNull(enrollments);
        assertTrue(enrollments.isEmpty());
    }

    @Test
    void testListEnrollments_WithMultipleEnrollments() {
        var student1 = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var student2 = studentService.addStudent("Jane", "Smith", "jane@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);

        enrollmentService.enrollStudentInCourse(student1.getId(), course.getId());
        enrollmentService.enrollStudentInCourse(student2.getId(), course.getId());

        List<Enrollment> enrollments = enrollmentService.listEnrollments();

        assertNotNull(enrollments);
        assertEquals(2, enrollments.size());
    }

    @Test
    void testListEnrollments_ReturnsUnmodifiableList() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        List<Enrollment> enrollments = enrollmentService.listEnrollments();

        assertThrows(UnsupportedOperationException.class, () -> enrollments.add(null));
    }

    @Test
    void testListEnrollmentsByStudentId_ExistingStudent() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course1 = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        var course2 = courseService.addCourse("Python Advanced", "Learn advanced Python concepts", 10);

        enrollmentService.enrollStudentInCourse(student.getId(), course1.getId());
        enrollmentService.enrollStudentInCourse(student.getId(), course2.getId());

        List<Enrollment> enrollments = enrollmentService.listEnrollmentsByStudentId(student.getId());

        assertNotNull(enrollments);
        assertEquals(2, enrollments.size());
    }

    @Test
    void testListEnrollmentsByStudentId_NonExistentStudent() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> enrollmentService.listEnrollmentsByStudentId(9999));
        assertEquals("Student not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testListEnrollmentsByStudentId_NoEnrollments() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        List<Enrollment> enrollments = enrollmentService.listEnrollmentsByStudentId(student.getId());

        assertNotNull(enrollments);
        assertTrue(enrollments.isEmpty());
    }

    @Test
    void testFindEnrollmentById_ExistingEnrollment() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        Enrollment addedEnrollment = enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        Enrollment foundEnrollment = enrollmentService.findEnrollmentById(addedEnrollment.getId());

        assertNotNull(foundEnrollment);
        assertEquals(addedEnrollment.getId(), foundEnrollment.getId());
    }

    @Test
    void testFindEnrollmentById_NonExistentEnrollment() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> enrollmentService.findEnrollmentById(9999));
        assertEquals("Enrollment not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testMarkEnrollmentCompleted_ExistingEnrollment() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        Enrollment enrollment = enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        enrollmentService.markEnrollmentCompleted(enrollment.getId());

        Enrollment completed = enrollmentService.findEnrollmentById(enrollment.getId());
        assertEquals(EnrollmentStatus.COMPLETED, completed.getStatus());
    }

    @Test
    void testMarkEnrollmentCompleted_NonExistentEnrollment() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> enrollmentService.markEnrollmentCompleted(9999));
        assertEquals("Enrollment not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testCancelEnrollment_ExistingEnrollment() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        Enrollment enrollment = enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        enrollmentService.cancelEnrollment(enrollment.getId());

        Enrollment cancelled = enrollmentService.findEnrollmentById(enrollment.getId());
        assertEquals(EnrollmentStatus.CANCELLED, cancelled.getStatus());
    }

    @Test
    void testCancelEnrollment_NonExistentEnrollment() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> enrollmentService.cancelEnrollment(9999));
        assertEquals("Enrollment not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testHasEnrollments_Empty() {
        assertFalse(enrollmentService.hasEnrollments());
    }

    @Test
    void testHasEnrollments_WithEnrollments() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        assertTrue(enrollmentService.hasEnrollments());
    }

    @Test
    void testEnrollmentStatusTransitions() {
        var student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        var course = courseService.addCourse("Java Basics", "Learn Java fundamentals", 8);
        Enrollment enrollment = enrollmentService.enrollStudentInCourse(student.getId(), course.getId());

        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());

        enrollmentService.markEnrollmentCompleted(enrollment.getId());
        Enrollment completed = enrollmentService.findEnrollmentById(enrollment.getId());
        assertEquals(EnrollmentStatus.COMPLETED, completed.getStatus());

        enrollmentService.cancelEnrollment(enrollment.getId());
        Enrollment cancelled = enrollmentService.findEnrollmentById(enrollment.getId());
        assertEquals(EnrollmentStatus.CANCELLED, cancelled.getStatus());
    }
}

