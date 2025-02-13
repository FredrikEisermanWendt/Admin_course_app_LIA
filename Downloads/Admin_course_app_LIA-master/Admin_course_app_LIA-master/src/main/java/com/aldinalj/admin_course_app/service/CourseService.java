package com.aldinalj.admin_course_app.service;

import com.aldinalj.admin_course_app.model.Course;
import com.aldinalj.admin_course_app.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course createOrUpdateCourse(Course course) {
        if (course.getId() == null) {
            if (course.getStartDate().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Start date cannot be in the past for a new course.");
            }
        }

        return courseRepository.save(course);
    }
}