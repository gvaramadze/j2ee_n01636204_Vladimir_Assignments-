package com.example.assign3vladimir.service;

import com.example.assign3vladimir.model.Admin;
import com.example.assign3vladimir.model.Student;
import com.example.assign3vladimir.repository.AdminRepository;
import com.example.assign3vladimir.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepo;
    private final StudentRepository studentRepo;
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    public Admin login(String username, String password) {
        return adminRepo.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student updateStudent(Long id, Student updated) {
        Student student = studentRepo.findById(id).orElseThrow();
        student.setName(updated.getName());
        student.setEmail(updated.getEmail());
        student.setAddress(updated.getAddress());
        student.setModifiedDate(LocalDateTime.now());
        log.info("Admin updated student: {}", student.getEmail());
        return studentRepo.save(student);
    }

    public void softDeleteStudent(Long id) {
        Student student = studentRepo.findById(id).orElseThrow();
        student.setDeleted(true);
        student.setModifiedDate(LocalDateTime.now());
        studentRepo.save(student);
        log.warn("Admin soft-deleted student: {}", student.getEmail());
    }
}