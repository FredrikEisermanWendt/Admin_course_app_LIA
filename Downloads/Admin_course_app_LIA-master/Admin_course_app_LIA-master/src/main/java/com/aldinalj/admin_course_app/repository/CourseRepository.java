package com.aldinalj.admin_course_app.repository;

import com.aldinalj.admin_course_app.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


}
