package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = context.getBean(Student.class);

        System.out.println("----- STUDENT DETAILS -----");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Gender: " + student.getGender());

        Certification cert = student.getCertification();

        System.out.println("----- CERTIFICATION DETAILS -----");
        System.out.println("Cert ID: " + cert.getId());
        System.out.println("Cert Name: " + cert.getName());
        System.out.println("Completed On: " + cert.getDateOfCompletion());
    }
}