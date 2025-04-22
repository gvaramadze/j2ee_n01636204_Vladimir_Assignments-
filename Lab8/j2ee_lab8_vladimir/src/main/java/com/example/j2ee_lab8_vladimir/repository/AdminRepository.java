package com.example.j2ee_lab8_vladimir.repository;

import com.example.j2ee_lab8_vladimir.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsernameAndPassword(String username, String password);
}