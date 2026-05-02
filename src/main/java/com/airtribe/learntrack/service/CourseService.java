package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseService {
    private final ArrayList<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        InputValidator.requireNonBlank(courseName, "Course name");
        InputValidator.requireNonBlank(description, "Description");
        InputValidator.requirePositive(durationInWeeks, "Duration in weeks");

        Course course = new Course(IdGenerator.getNextCourseId(), courseName, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public List<Course> listCourses() {
        return Collections.unmodifiableList(courses);
    }

    public Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course not found with ID: " + courseId);
    }

    public void activateCourse(int courseId) {
        Course course = findCourseById(courseId);
        course.setActive(true);
    }

    public void deactivateCourse(int courseId) {
        Course course = findCourseById(courseId);
        course.setActive(false);
    }

    public boolean hasCourses() {
        return !courses.isEmpty();
    }
}
