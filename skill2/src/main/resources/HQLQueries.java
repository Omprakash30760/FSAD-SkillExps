package com.hql;

import org.hibernate.*;
import java.util.*;

public class HQLQueries 
{
    public static void main(String[] args) 
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        System.out.println("----- Sorting by Price ASC -----");
        List list1 = session.createQuery("from Product order by price asc").list();
        list1.forEach(System.out::println);

        System.out.println("----- Sorting by Price DESC -----");
        List list2 = session.createQuery("from Product order by price desc").list();
        list2.forEach(System.out::println);

        System.out.println("----- Sort by Quantity (HIGH → LOW) -----");
        List list3 = session.createQuery("from Product order by quantity desc").list();
        list3.forEach(System.out::println);

        System.out.println("----- Pagination (First 3) -----");
        Query q4 = session.createQuery("from Product");
        q4.setFirstResult(0);
        q4.setMaxResults(3);
        q4.list().forEach(System.out::println);

        System.out.println("----- Pagination (Next 3) -----");
        Query q5 = session.createQuery("from Product");
        q5.setFirstResult(3);
        q5.setMaxResults(3);
        q5.list().forEach(System.out::println);

        System.out.println("----- Aggregates -----");
        Long total = (Long) session.createQuery("select count(*) from Product").uniqueResult();
        System.out.println("Total Products: " + total);

        Long qtyAboveZero = (Long) session.createQuery("select count(*) from Product where quantity > 0").uniqueResult();
        System.out.println("Products with qty > 0: " + qtyAboveZero);

        System.out.println("Count group by description:");
        List groupCount = session.createQuery("select description, count(*) from Product group by description").list();
        groupCount.forEach(System.out::println);

        Double minPrice = (Double) session.createQuery("select min(price) from Product").uniqueResult();
        Double maxPrice = (Double) session.createQuery("select max(price) from Product").uniqueResult();
        System.out.println("Min Price: " + minPrice);
        System.out.println("Max Price: " + maxPrice);

        System.out.println("----- GROUP BY Description -----");
        List groupBy = session.createQuery("select description from Product group by description").list();
        groupBy.forEach(System.out::println);

        System.out.println("----- WHERE Price Range -----");
        List range = session.createQuery("from Product where price between 1000 and 30000").list();
        range.forEach(System.out::println);

        System.out.println("----- LIKE Queries -----");
        List like1 = session.createQuery("from Product where name like 'K%'").list();
        List like2 = session.createQuery("from Product where name like '%r'").list();
        List like3 = session.createQuery("from Product where name like '%top%'").list();
        List like4 = session.createQuery("from Product where length(name) = 5").list();

        System.out.println("Names starting with K: " + like1);
        System.out.println("Names ending with r: " + like2);
        System.out.println("Names containing 'top': " + like3);
        System.out.println("Names with length 5: " + like4);

        session.close();
    }
}