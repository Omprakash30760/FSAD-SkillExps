package com.klu.di;

public class Student {

    private int studentId;
    private String name;
    private String course;
    private String year;

    // Constructor Injection
    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // Setter Injection
    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(String year) {
        this.year = year;
    }

    // Display Method
    public void display() {
        System.out.println("---- XML Based DI ----");
        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("Course     : " + course);
        System.out.println("Year       : " + year);
    }
}