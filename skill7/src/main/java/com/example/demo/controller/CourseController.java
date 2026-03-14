package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<Object> addCourse(@RequestBody Course course) {
        Course saved = service.addCourse(course);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Course course) {
        Course updated = service.updateCourse(id, course);

        if (updated == null)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        boolean deleted = service.deleteCourse(id);

        if (!deleted)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) {
        Course course = service.getCourse(id);

        if (course == null)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<Object> search(@PathVariable String title) {
        List<Course> results = service.searchByTitle(title);

        if (results.isEmpty())
            return new ResponseEntity<>("No matching courses found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}