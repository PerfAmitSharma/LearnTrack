package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    handleStudentMenu();
                    break;
                case 2:
                    handleCourseMenu();
                    break;
                case 3:
                    handleEnrollmentMenu();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using LearnTrack.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("========== LearnTrack ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Exit");
    }

    private void handleStudentMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("------ Student Management ------");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Deactivate student");
            System.out.println("6. Back");

            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        printStudents(studentService.listStudents());
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deactivateStudent();
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void handleCourseMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("------ Course Management ------");
            System.out.println("1. Add new course");
            System.out.println("2. View all courses");
            System.out.println("3. Search course by ID");
            System.out.println("4. Activate course");
            System.out.println("5. Deactivate course");
            System.out.println("6. Back");

            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        printCourses(courseService.listCourses());
                        break;
                    case 3:
                        searchCourseById();
                        break;
                    case 4:
                        activateCourse();
                        break;
                    case 5:
                        deactivateCourse();
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void handleEnrollmentMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("------ Enrollment Management ------");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View all enrollments");
            System.out.println("3. View enrollments by student ID");
            System.out.println("4. Mark enrollment completed");
            System.out.println("5. Cancel enrollment");
            System.out.println("6. Back");

            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1:
                        enrollStudentInCourse();
                        break;
                    case 2:
                        printEnrollments(enrollmentService.listEnrollments());
                        break;
                    case 3:
                        viewEnrollmentsByStudentId();
                        break;
                    case 4:
                        markEnrollmentCompleted();
                        break;
                    case 5:
                        cancelEnrollment();
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void addStudent() {
        String firstName = readText("First name: ");
        String lastName = readText("Last name: ");
        String email = readText("Email: ");
        String batch = readText("Batch: ");

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("Student added successfully: " + student);
    }

    private void searchStudentById() {
        int studentId = readInt("Student ID: ");
        System.out.println(studentService.findStudentById(studentId));
    }

    private void updateStudent() {
        int studentId = readInt("Student ID to update: ");
        String firstName = readText("Updated first name: ");
        String lastName = readText("Updated last name: ");
        String email = readText("Updated email: ");
        String batch = readText("Updated batch: ");

        Student student = studentService.updateStudent(studentId, firstName, lastName, email, batch);
        System.out.println("Student updated successfully: " + student);
    }

    private void deactivateStudent() {
        int studentId = readInt("Student ID to deactivate: ");
        studentService.deactivateStudent(studentId);
        System.out.println("Student deactivated successfully.");
    }

    private void addCourse() {
        String courseName = readText("Course name: ");
        String description = readText("Description: ");
        int durationInWeeks = readInt("Duration in weeks: ");

        Course course = courseService.addCourse(courseName, description, durationInWeeks);
        System.out.println("Course added successfully: " + course);
    }

    private void searchCourseById() {
        int courseId = readInt("Course ID: ");
        System.out.println(courseService.findCourseById(courseId));
    }

    private void activateCourse() {
        int courseId = readInt("Course ID to activate: ");
        courseService.activateCourse(courseId);
        System.out.println("Course activated successfully.");
    }

    private void deactivateCourse() {
        int courseId = readInt("Course ID to deactivate: ");
        courseService.deactivateCourse(courseId);
        System.out.println("Course deactivated successfully.");
    }

    private void enrollStudentInCourse() {
        printStudents(studentService.listStudents());
        printCourses(courseService.listCourses());

        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");

        Enrollment enrollment = enrollmentService.enrollStudentInCourse(studentId, courseId);
        System.out.println("Enrollment created successfully: " + enrollment);
    }

    private void viewEnrollmentsByStudentId() {
        int studentId = readInt("Student ID: ");
        printEnrollments(enrollmentService.listEnrollmentsByStudentId(studentId));
    }

    private void markEnrollmentCompleted() {
        int enrollmentId = readInt("Enrollment ID to complete: ");
        enrollmentService.markEnrollmentCompleted(enrollmentId);
        System.out.println("Enrollment marked as completed.");
    }

    private void cancelEnrollment() {
        int enrollmentId = readInt("Enrollment ID to cancel: ");
        enrollmentService.cancelEnrollment(enrollmentId);
        System.out.println("Enrollment cancelled successfully.");
    }

    private void printStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void printCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void printEnrollments(List<Enrollment> enrollments) {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
