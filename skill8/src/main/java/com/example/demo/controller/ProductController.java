package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    // Insert sample data
    @PostMapping("/add-sample")
    public String addSample() {
        repo.save(new Product("Laptop", "Electronics", 55000));
        repo.save(new Product("Phone", "Electronics", 20000));
        repo.save(new Product("Shirt", "Clothing", 1500));
        repo.save(new Product("Shoes", "Clothing", 2500));
        repo.save(new Product("Fridge", "Appliance", 40000));
        return "Sample Products Added!";
    }

    // 1. Get by Category
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    // 2. Price Filter (min-max)
    @GetMapping("/filter")
    public List<Product> getByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        return repo.findByPriceBetween(min, max);
    }

    // 3. Sorted by Price
    @GetMapping("/sorted")
    public List<Product> getSortedByPrice() {
        return repo.sortByPrice();
    }

    // 4. Expensive Products Above a Price
    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable double price) {
        return repo.findExpensiveProducts(price);
    }
}