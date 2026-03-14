package com.example.demo.service;

import com.example.demo.entity.Course;
import java.util.List;

public interface CourseService {

    Course addCourse(Course course);

    Course updateCourse(int id, Course course);

    boolean deleteCourse(int id);

    Course getCourse(int id);

    List<Course> getAllCourses();

    List<Course> searchByTitle(String title);
}