package com.klu.hibernateCRUD;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product("Laptop", "Electronics", 1200, 10);
        Product p2 = new Product("Phone", "Electronics", 800, 15);
        Product p3 = new Product("Chair", "Furniture", 150, 20);
        Product p4 = new Product("Table", "Furniture", 250, 5);
        Product p5 = new Product("Pen", "Stationery", 5, 100);
        Product p6 = new Product("Notebook", "Stationery", 20, 50);

        session.save(p1);
        session.save(p2);
        session.save(p3);
        session.save(p4);
        session.save(p5);
        session.save(p6);

        tx.commit();

        List<Product> productsAsc = session.createQuery("FROM Product ORDER BY price ASC", Product.class).list();
        List<Product> productsDesc = session.createQuery("FROM Product ORDER BY price DESC", Product.class).list();
        List<Product> productsQty = session.createQuery("FROM Product ORDER BY quantity DESC", Product.class).list();

        List<Product> firstThree = session.createQuery("FROM Product", Product.class)
                                          .setFirstResult(0)
                                          .setMaxResults(3)
                                          .list();
        List<Product> nextThree = session.createQuery("FROM Product", Product.class)
                                         .setFirstResult(3)
                                         .setMaxResults(3)
                                         .list();

        Long totalProducts = session.createQuery("SELECT COUNT(*) FROM Product", Long.class).getSingleResult();
        Long availableProducts = session.createQuery("SELECT COUNT(*) FROM Product WHERE quantity > 0", Long.class).getSingleResult();
        Double minPrice = session.createQuery("SELECT MIN(price) FROM Product", Double.class).getSingleResult();
        Double maxPrice = session.createQuery("SELECT MAX(price) FROM Product", Double.class).getSingleResult();

        List<Object[]> countByDescription = session.createQuery("SELECT description, COUNT(*) FROM Product GROUP BY description").list();
        List<Product> productsInRange = session.createQuery("FROM Product WHERE price BETWEEN 100 AND 1000", Product.class).list();

        List<Product> startWithP = session.createQuery("FROM Product WHERE name LIKE 'P%'", Product.class).list();
        List<Product> endWithE = session.createQuery("FROM Product WHERE name LIKE '%e'", Product.class).list();
        List<Product> containTop = session.createQuery("FROM Product WHERE name LIKE '%top%'", Product.class).list();
        List<Product> exactLength4 = session.createQuery("FROM Product WHERE LENGTH(name) = 4", Product.class).list();

        System.out.println("=== Total Products: " + totalProducts);
        System.out.println("=== Products with quantity > 0: " + availableProducts);
        System.out.println("=== Min Price: " + minPrice + ", Max Price: " + maxPrice);

        System.out.println("\n--- Products Ascending by Price ---");
        productsAsc.forEach(p -> System.out.println(p.getName() + " : " + p.getPrice()));

        System.out.println("\n--- Products Descending by Price ---");
        productsDesc.forEach(p -> System.out.println(p.getName() + " : " + p.getPrice()));

        System.out.println("\n--- Products by Quantity Desc ---");
        productsQty.forEach(p -> System.out.println(p.getName() + " : " + p.getQuantity()));

        System.out.println("\n--- First 3 Products (Pagination) ---");
        firstThree.forEach(p -> System.out.println(p.getName()));

        System.out.println("\n--- Next 3 Products (Pagination) ---");
        nextThree.forEach(p -> System.out.println(p.getName()));

        System.out.println("\n--- Count by Description ---");
        for(Object[] row : countByDescription) {
            System.out.println(row[0] + " : " + row[1]);
        }

        System.out.println("\n--- Products in Price Range 100-1000 ---");
        productsInRange.forEach(p -> System.out.println(p.getName() + " : " + p.getPrice()));

        System.out.println("\n--- Products LIKE Queries ---");
        System.out.print("Start with P: "); startWithP.forEach(p -> System.out.print(p.getName() + " "));
        System.out.print("\nEnd with e: "); endWithE.forEach(p -> System.out.print(p.getName() + " "));
        System.out.print("\nContain 'top': "); containTop.forEach(p -> System.out.print(p.getName() + " "));
        System.out.print("\nExact length 4: "); exactLength4.forEach(p -> System.out.print(p.getName() + " "));

        session.close();
    }
}