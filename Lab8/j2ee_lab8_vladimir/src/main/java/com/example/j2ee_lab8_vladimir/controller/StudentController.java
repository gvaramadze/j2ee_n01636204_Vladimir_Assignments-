package com.example.j2ee_lab8_vladimir.controller;

import com.example.j2ee_lab8_vladimir.dto.LoginRequest;
import com.example.j2ee_lab8_vladimir.model.Course;
import com.example.j2ee_lab8_vladimir.model.Student;
import com.example.j2ee_lab8_vladimir.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Student student = studentService.login(loginRequest.getEmail(), loginRequest.getPassword());
            log.info("Login successful for student: {}", student.getEmail());
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            log.warn("Student login failed for: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateProfile(@PathVariable Long id, @RequestBody Student newData) {
        return ResponseEntity.ok(studentService.updateProfile(id, newData));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> viewCourses() {
        return ResponseEntity.ok(studentService.viewCourses());
    }

    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<Student> enroll(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.enrollCourse(studentId, courseId));
    }

    @PostMapping("/drop/{studentId}/{courseId}")
    public ResponseEntity<Student> drop(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.dropCourse(studentId, courseId));
    }
}