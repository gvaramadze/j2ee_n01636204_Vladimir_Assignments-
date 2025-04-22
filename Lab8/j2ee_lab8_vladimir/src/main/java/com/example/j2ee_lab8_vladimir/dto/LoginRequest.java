package com.example.j2ee_lab8_vladimir.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}