package com.example.assign3vladimir.repository;

import com.example.assign3vladimir.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}