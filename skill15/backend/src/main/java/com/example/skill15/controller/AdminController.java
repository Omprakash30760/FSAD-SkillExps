package com.example.skill15.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestParam(defaultValue = "new-employee") String employeeName) {
        return ResponseEntity.ok("Employee added by ADMIN: " + employeeName);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam(defaultValue = "unknown-id") String employeeId) {
        return ResponseEntity.ok("Employee deleted by ADMIN: " + employeeId);
    }
}
