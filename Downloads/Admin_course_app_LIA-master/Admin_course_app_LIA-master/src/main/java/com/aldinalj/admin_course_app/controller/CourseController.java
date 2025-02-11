package com.aldinalj.admin_course_app.controller;

import com.aldinalj.admin_course_app.model.Course;
import com.aldinalj.admin_course_app.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdateCourse(@RequestBody @Valid Course course) {
        try {
            Course savedCourse = courseService.createOrUpdateCourse(course);
            return ResponseEntity.ok(savedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}