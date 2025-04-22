package com.example.j2ee_lab8_vladimir.repository;

import com.example.j2ee_lab8_vladimir.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}