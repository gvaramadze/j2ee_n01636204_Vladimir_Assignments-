package com.example.j2eelab3_vladimir.services;

import com.example.j2eelab3_vladimir.model.Employee;
import com.example.j2eelab3_vladimir.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public void updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = existingEmployee.get();

            // Update only non-null fields
            if (updatedEmployee.getFirstName() != null) {
                employeeToUpdate.setFirstName(updatedEmployee.getFirstName());
            }
            if (updatedEmployee.getLastName() != null) {
                employeeToUpdate.setLastName(updatedEmployee.getLastName());
            }
            if (updatedEmployee.getEmailId() != null) {
                employeeToUpdate.setEmailId(updatedEmployee.getEmailId());
            }

            employeeRepository.save(employeeToUpdate);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
