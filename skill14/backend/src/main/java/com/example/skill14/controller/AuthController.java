package com.example.skill14.controller;

import com.example.skill14.dto.AuthResponse;
import com.example.skill14.dto.LoginRequest;
import com.example.skill14.dto.RegisterRequest;
import com.example.skill14.model.User;
import com.example.skill14.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Optional<User> user = userService.register(request);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Registration failed. Check data or uniqueness.");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(user.get().getId(), user.get().getUsername(), "Registration successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.login(request);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        return ResponseEntity.ok(new AuthResponse(user.get().getId(), user.get().getUsername(), "Login successful"));
    }
}
