package com.aldinalj.admin_course_app.controller;

import com.aldinalj.admin_course_app.model.User;
import com.aldinalj.admin_course_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdateCourse(@RequestBody @Valid User user){
        try {
            userService.createOrUpdateUser(user);
            return ResponseEntity.ok().body(200);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
