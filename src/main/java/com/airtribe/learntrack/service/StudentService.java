package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentService {
    private final ArrayList<Student> students = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireEmailLike(email);
        InputValidator.requireNonBlank(batch, "Batch");

        Student student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireNonBlank(batch, "Batch");

        Student student = new Student(IdGenerator.getNextStudentId(), firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public List<Student> listStudents() {
        return Collections.unmodifiableList(students);
    }

    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student not found with ID: " + studentId);
    }

    public Student updateStudent(int studentId, String firstName, String lastName, String email, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireEmailLike(email);
        InputValidator.requireNonBlank(batch, "Batch");

        Student student = findStudentById(studentId);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        return student;
    }

    public void deactivateStudent(int studentId) {
        Student student = findStudentById(studentId);
        student.setActive(false);
    }

    public boolean hasStudents() {
        return !students.isEmpty();
    }
}
