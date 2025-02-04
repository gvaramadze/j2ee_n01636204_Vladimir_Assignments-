package com.example.j2eelab3_vladimir.model;

import jakarta.validation.constraints.*;

public class Employee {
    private int id;

    @NotBlank(message = "First Name cannot be empty or blank")
    @Size(min = 5, max = 20, message = "First Name must be between 5 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String emailId;

    public Employee() {}

    public Employee(int id, String firstName, String lastName, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
}
