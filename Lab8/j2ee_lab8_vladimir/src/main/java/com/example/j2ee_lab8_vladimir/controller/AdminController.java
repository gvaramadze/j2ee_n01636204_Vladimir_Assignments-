package com.example.j2ee_lab8_vladimir.controller;

import com.example.j2ee_lab8_vladimir.dto.LoginRequest;
import com.example.j2ee_lab8_vladimir.model.Admin;
import com.example.j2ee_lab8_vladimir.model.Student;
import com.example.j2ee_lab8_vladimir.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Admin admin = adminService.login(loginRequest.getEmail(), loginRequest.getPassword());
            log.info("Login successful for admin: {}", admin.getUsername());
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            log.warn("Admin login failed for: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(adminService.getAllStudents());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updated) {
        return ResponseEntity.ok(adminService.updateStudent(id, updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        adminService.softDeleteStudent(id);
        return ResponseEntity.ok("Student soft-deleted");
    }
}