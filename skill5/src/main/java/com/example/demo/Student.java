package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id = 101;
    private String name = "R.prasad";
    private String gender = "Male";

    @Autowired
    private Certification certification;   // Auto-injected

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public Certification getCertification() { return certification; }
}