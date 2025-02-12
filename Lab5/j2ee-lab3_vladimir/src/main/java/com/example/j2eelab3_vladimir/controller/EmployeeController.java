package com.example.j2eelab3_vladimir.controller;

import com.example.j2eelab3_vladimir.model.Employee;
import com.example.j2eelab3_vladimir.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.Optional;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        model.addAttribute("employeeList", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showAddEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "addEmployee";
        }
        employeeService.addEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
        if (employeeOptional.isPresent()) {
            model.addAttribute("employee", employeeOptional.get());
            return "updateEmployee";
        } else {
            return "redirect:/";
        }
    }

    @PutMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable Long id, @Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "updateEmployee";
        }
        employeeService.updateEmployee(id, employee);
        return "redirect:/";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/findEmployeeById")
    public String findEmployeeByIdForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "findEmployee";
    }

    @PostMapping("/findEmployee")
    public String findEmployee(@ModelAttribute Employee employee, Model model) {
        Optional<Employee> foundEmployee = employeeService.getEmployeeById(employee.getId());
        model.addAttribute("foundEmployee", foundEmployee.orElse(null));
        return "findEmployee";
    }
}
