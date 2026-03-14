package com.hql;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class InsertProducts 
{
    public static void main(String[] args) 
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product(); p1.setId(1); p1.setName("Laptop"); p1.setDescription("Electronics"); p1.setPrice(55000); p1.setQuantity(10);
        Product p2 = new Product(); p2.setId(2); p2.setName("Phone"); p2.setDescription("Electronics"); p2.setPrice(30000); p2.setQuantity(15);
        Product p3 = new Product(); p3.setId(3); p3.setName("Mouse"); p3.setDescription("Accessories"); p3.setPrice(800); p3.setQuantity(50);
        Product p4 = new Product(); p4.setId(4); p4.setName("Keyboard"); p4.setDescription("Accessories"); p4.setPrice(1500); p4.setQuantity(30);
        Product p5 = new Product(); p5.setId(5); p5.setName("Bag"); p5.setDescription("Travel"); p5.setPrice(1200); p5.setQuantity(12);
        Product p6 = new Product(); p6.setId(6); p6.setName("Charger"); p6.setDescription("Electronics"); p6.setPrice(700); p6.setQuantity(20);

        session.save(p1);
        session.save(p2);
        session.save(p3);
        session.save(p4);
        session.save(p5);
        session.save(p6);

        tx.commit();
        session.close();

        System.out.println("Products Inserted Successfully!");
    }
}