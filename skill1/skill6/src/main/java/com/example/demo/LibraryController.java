package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {

    List<Book> books = new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Skill 6 Library API";
    }

    @GetMapping("/count")
    public int count() {
        return 50;
    }

    @GetMapping("/price")
    public double price() {
        return 299.99;
    }

    @GetMapping("/books")
    public List<String> getBooks() {
        return Arrays.asList("Java", "Spring", "Python");
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        return "Book Details for ID: " + id;
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "You searched for: " + title;
    }

    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by: " + name;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        books.add(book);
        return "Book added: " + book.getTitle();
    }

    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return books;
    }
}