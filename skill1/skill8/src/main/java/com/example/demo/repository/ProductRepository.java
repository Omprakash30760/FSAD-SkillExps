package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived Query Methods
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL Queries

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> sortByPrice();

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findExpensiveProducts(double price);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategoryJPQL(String category);
}