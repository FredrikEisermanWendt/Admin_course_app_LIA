package com.aldinalj.admin_course_app.controller;
import com.aldinalj.admin_course_app.model.Course;
import com.aldinalj.admin_course_app.repository.CourseRepository;
import com.aldinalj.admin_course_app.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    public CourseController(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @Operation(summary = "Create course")
    @PostMapping
    public ResponseEntity<?> createOrUpdateCourse(@RequestBody @Valid Course course) {
        try {
            Course savedCourse = courseService.createOrUpdateCourse(course);
            return ResponseEntity.ok(savedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Update existing course in database")
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

    @Operation(summary = "Deletes course from database")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        try {
            if (courseRepository.existsById(id)) {
                courseRepository.deleteById(id);
                return ResponseEntity.status(200).body("Course deleted successfully.");
            } else {
                return ResponseEntity.status(404).body("Course not found.");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}