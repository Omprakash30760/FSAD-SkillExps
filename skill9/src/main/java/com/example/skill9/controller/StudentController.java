package com.example.skill9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.skill9.model.Student;
import com.example.skill9.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.getStudent(id);
    }
}