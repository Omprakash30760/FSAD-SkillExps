package com.example.skill15.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/profile")
    public ResponseEntity<String> employeeProfile(Authentication authentication) {
        return ResponseEntity.ok("Profile data for EMPLOYEE user: " + authentication.getName());
    }
}
