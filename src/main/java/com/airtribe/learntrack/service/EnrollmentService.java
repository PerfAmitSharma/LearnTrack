package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnrollmentService {
    private final ArrayList<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudentInCourse(int studentId, int courseId) {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student.");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course.");
        }
        if (hasActiveEnrollment(studentId, courseId)) {
            throw new InvalidInputException("Student is already actively enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment(
                IdGenerator.getNextEnrollmentId(),
                studentId,
                courseId,
                LocalDate.now(),
                EnrollmentStatus.ACTIVE
        );
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> listEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    public List<Enrollment> listEnrollmentsByStudentId(int studentId) {
        studentService.findStudentById(studentId);

        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }
        return Collections.unmodifiableList(studentEnrollments);
    }

    public Enrollment findEnrollmentById(int enrollmentId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == enrollmentId) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId);
    }

    public void markEnrollmentCompleted(int enrollmentId) {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void cancelEnrollment(int enrollmentId) {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }

    public boolean hasEnrollments() {
        return !enrollments.isEmpty();
    }

    private boolean hasActiveEnrollment(int studentId, int courseId) {
        for (Enrollment enrollment : enrollments) {
            boolean sameStudent = enrollment.getStudentId() == studentId;
            boolean sameCourse = enrollment.getCourseId() == courseId;
            boolean active = enrollment.getStatus() == EnrollmentStatus.ACTIVE;

            if (sameStudent && sameCourse && active) {
                return true;
            }
        }
        return false;
    }
}
