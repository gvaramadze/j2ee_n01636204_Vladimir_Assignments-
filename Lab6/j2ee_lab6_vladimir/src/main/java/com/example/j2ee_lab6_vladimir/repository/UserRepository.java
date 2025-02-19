package com.example.j2ee_lab6_vladimir.repository;

import com.example.j2ee_lab6_vladimir.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
