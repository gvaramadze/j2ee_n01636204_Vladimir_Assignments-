package com.example.j2eelab3_vladimir.controller;

import com.example.j2eelab3_vladimir.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;


import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        model.addAttribute("employeeList", employees);
        return "index";
    }

    @GetMapping("/showAddEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addEmployee";
        }
        employees.add(employee);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(new Employee());
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee updatedEmployee) {
        employees.removeIf(e -> e.getId() == updatedEmployee.getId());
        employees.add(updatedEmployee);
        return "redirect:/";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employees.removeIf(e -> e.getId() == id);
        return "redirect:/";
    }

    @GetMapping("/findEmployeeById")
    public String findEmployeeByIdForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "findEmployee";
    }

    @PostMapping("/findEmployee")
    public String findEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        Employee foundEmployee = employees.stream()
                .filter(e -> e.getId() == employee.getId())
                .findFirst()
                .orElse(null);

        model.addAttribute("foundEmployee", foundEmployee);
        return "findEmployee";
    }

}