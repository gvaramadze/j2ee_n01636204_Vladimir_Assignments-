package com.example.j2ee_lab6_vladimir.services;

import com.example.j2ee_lab6_vladimir.models.User;
import com.example.j2ee_lab6_vladimir.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        logger.info("Adding new user: {}", user.getUsername());
        userRepository.save(user);
    }

    public User userLogin(String username, String password) {
        logger.info("Attempting login for user: {}", username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            logger.info("User {} logged in successfully", username);
            return userOptional.get();
        }
        logger.info("Login failed for user: {}", username);
        return null;
    }
}
