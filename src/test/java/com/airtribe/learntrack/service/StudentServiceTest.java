package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void testAddStudent_WithValidData() {
        Student student = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        assertNotNull(student);
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john@example.com", student.getEmail());
        assertEquals("Batch-2024", student.getBatch());
        assertTrue(student.isActive());
    }

    @Test
    void testAddStudent_WithoutEmail() {
        Student student = studentService.addStudent("John", "Doe", "Batch-2024");

        assertNotNull(student);
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("", student.getEmail());
        assertEquals("Batch-2024", student.getBatch());
        assertTrue(student.isActive());
    }

    @Test
    void testAddStudent_WithBlankFirstName() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> studentService.addStudent("", "Doe", "john@example.com", "Batch-2024"));
        assertEquals("First name cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddStudent_WithBlankLastName() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> studentService.addStudent("John", "", "john@example.com", "Batch-2024"));
        assertEquals("Last name cannot be empty.", exception.getMessage());
    }

    @Test
    void testAddStudent_WithInvalidEmail() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> studentService.addStudent("John", "Doe", "invalid-email", "Batch-2024"));
        assertEquals("Please enter a valid email address.", exception.getMessage());
    }

    @Test
    void testAddStudent_WithBlankBatch() {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> studentService.addStudent("John", "Doe", "john@example.com", ""));
        assertEquals("Batch cannot be empty.", exception.getMessage());
    }

    @Test
    void testListStudents_Empty() {
        List<Student> students = studentService.listStudents();

        assertNotNull(students);
        assertTrue(students.isEmpty());
    }

    @Test
    void testListStudents_WithMultipleStudents() {
        studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        studentService.addStudent("Jane", "Smith", "jane@example.com", "Batch-2024");

        List<Student> students = studentService.listStudents();

        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void testListStudents_ReturnsUnmodifiableList() {
        studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        List<Student> students = studentService.listStudents();

        assertThrows(UnsupportedOperationException.class, () -> students.add(null));
    }

    @Test
    void testFindStudentById_ExistingStudent() {
        Student added = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        Student found = studentService.findStudentById(added.getId());

        assertNotNull(found);
        assertEquals(added.getId(), found.getId());
        assertEquals("John", found.getFirstName());
    }

    @Test
    void testFindStudentById_NonExistentStudent() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.findStudentById(9999));
        assertEquals("Student not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testUpdateStudent_WithValidData() {
        Student added = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        Student updated = studentService.updateStudent(added.getId(), "Jane", "Smith", "jane@example.com", "Batch-2025");

        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("jane@example.com", updated.getEmail());
        assertEquals("Batch-2025", updated.getBatch());
    }

    @Test
    void testUpdateStudent_WithBlankData() {
        Student added = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> studentService.updateStudent(added.getId(), "", "Smith", "jane@example.com", "Batch-2025"));
        assertEquals("First name cannot be empty.", exception.getMessage());
    }

    @Test
    void testUpdateStudent_NonExistentStudent() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.updateStudent(9999, "Jane", "Smith", "jane@example.com", "Batch-2025"));
        assertEquals("Student not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testDeactivateStudent_ExistingStudent() {
        Student added = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        assertTrue(added.isActive());

        studentService.deactivateStudent(added.getId());

        Student deactivated = studentService.findStudentById(added.getId());
        assertFalse(deactivated.isActive());
    }

    @Test
    void testDeactivateStudent_NonExistentStudent() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.deactivateStudent(9999));
        assertEquals("Student not found with ID: 9999", exception.getMessage());
    }

    @Test
    void testHasStudents_Empty() {
        assertFalse(studentService.hasStudents());
    }

    @Test
    void testHasStudents_WithStudents() {
        studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");

        assertTrue(studentService.hasStudents());
    }

    @Test
    void testStudentUniqueIds() {
        Student s1 = studentService.addStudent("John", "Doe", "john@example.com", "Batch-2024");
        Student s2 = studentService.addStudent("Jane", "Smith", "jane@example.com", "Batch-2024");
        Student s3 = studentService.addStudent("Bob", "Johnson", "bob@example.com", "Batch-2025");

        assertNotEquals(s1.getId(), s2.getId());
        assertNotEquals(s2.getId(), s3.getId());
        assertNotEquals(s1.getId(), s3.getId());
    }
}

