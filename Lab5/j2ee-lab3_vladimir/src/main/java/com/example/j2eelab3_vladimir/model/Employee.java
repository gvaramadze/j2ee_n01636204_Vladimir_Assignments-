package com.example.j2eelab3_vladimir.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name cannot be empty or blank")
    @Size(min = 5, max = 20, message = "First Name must be between 5 and 20 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, unique = true)
    private String emailId;

}
