package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id = 5001;
    private String name = "Java Full Stack Certification";
    private String dateOfCompletion = "15-03-2026";

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDateOfCompletion() { return dateOfCompletion; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }
}