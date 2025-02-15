package com.aldinalj.admin_course_app.controller;
import com.aldinalj.admin_course_app.model.Course;
import com.aldinalj.admin_course_app.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Integer id, @RequestBody Course updatedCourse) {

        Optional<Course> course = courseService.getCourseById(id);

        Course existingCourse = course.get();

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setCode(updatedCourse.getCode());
        existingCourse.setStartDate(updatedCourse.getStartDate());
        existingCourse.setEndDate(updatedCourse.getEndDate());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setAdminId(updatedCourse.getAdminId());

        courseService.createOrUpdateCourse(existingCourse);

        return ResponseEntity.status(200).body("Course updated successfully.");
    }

}