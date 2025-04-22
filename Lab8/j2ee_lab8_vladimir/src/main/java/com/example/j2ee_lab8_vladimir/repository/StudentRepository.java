package com.example.j2ee_lab8_vladimir.repository;

import com.example.j2ee_lab8_vladimir.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailAndPasswordAndDeletedFalse(String email, String password);
}