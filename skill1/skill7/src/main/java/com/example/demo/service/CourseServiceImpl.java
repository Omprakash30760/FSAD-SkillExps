package com.example.demo.service;

import com.example.demo.entity.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private List<Course> courseList = new ArrayList<>();

    @Override
    public Course addCourse(Course course) {
        courseList.add(course);
        return course;
    }

    @Override
    public Course updateCourse(int id, Course updatedCourse) {
        for (Course c : courseList) {
            if (c.getCourseId() == id) {
                c.setTitle(updatedCourse.getTitle());
                c.setDuration(updatedCourse.getDuration());
                c.setFee(updatedCourse.getFee());
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseList.removeIf(c -> c.getCourseId() == id);
    }

    @Override
    public Course getCourse(int id) {
        return courseList.stream()
                .filter(c -> c.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseList;
    }

    @Override
    public List<Course> searchByTitle(String title) {
        List<Course> result = new ArrayList<>();

        for (Course c : courseList) {
            if (c.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }
}