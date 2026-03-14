package com.example.demo.controller;

import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        // Handle non-numeric input
        if (!id.matches("\\d+")) {
            throw new InvalidInputException("Student ID must be a number!");
        }

        int studentId = Integer.parseInt(id);

        // Only ID 1 is valid
        if (studentId != 1) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }

        // Return your custom student data
        return new Student(
                1,
                "Prasoona",
                "Computer Science"
        );
    }
}