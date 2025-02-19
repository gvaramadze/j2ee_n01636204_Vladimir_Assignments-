package com.example.j2ee_lab6_vladimir.controllers;

import com.example.j2ee_lab6_vladimir.models.User;
import com.example.j2ee_lab6_vladimir.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/signup")
    public String addNewUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        logger.info("Received request to add user: {}", user.getUsername());
        userService.addUser(user);
        model.addAttribute("response", "added");
        return "index";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String username, @RequestParam String password, Model model) {
        logger.info("Received request to login for user: {}", username);
        User user = userService.userLogin(username, password);
        if (!Objects.isNull(user)) {
            logger.info("User {}, logged in successfully", username);
            model.addAttribute("fullName", user.getFirstName() + " " + user.getLastName());
            return "home";
        } else {
            logger.info("Authentication failed for the user: {}", username);
            model.addAttribute("response", "Invalid username or password");
            return "index";
        }
    }
}
