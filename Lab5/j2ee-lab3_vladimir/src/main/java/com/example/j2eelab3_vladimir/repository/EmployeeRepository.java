package com.example.j2eelab3_vladimir.repository;

import com.example.j2eelab3_vladimir.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
