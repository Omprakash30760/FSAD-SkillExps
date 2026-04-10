package com.klu.ai;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private int studentId;
    private String name;
    private String course;
    private int year;

    public Student() {   // Constructor Injection
        this.studentId = 1;
        this.name = "Prasoona";
        this.course = "CSE";
        this.year = 2;
    }

    public void display() {
        System.out.println("ID : " + studentId);
        System.out.println("Name : " + name);
        System.out.println("Course : " + course);
        System.out.println("Year : " + year);
    }
}