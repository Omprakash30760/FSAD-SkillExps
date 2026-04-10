package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id = 123456;
    private String name = "Java Full Stack Certification";
    private String dateOfCompletion = "5-03-2025";

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDateOfCompletion() { return dateOfCompletion; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }
}