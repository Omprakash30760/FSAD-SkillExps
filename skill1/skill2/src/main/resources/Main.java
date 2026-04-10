package com.klu.hibernateCRUD;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(new Product("Laptop","Electronics",80000,10));
        session.persist(new Product("Mouse","Electronics",500,50));
        session.persist(new Product("Keyboard","Electronics",1200,30));
        session.persist(new Product("Monitor","Electronics",15000,15));
        session.persist(new Product("Chair","Furniture",5000,20));
        session.persist(new Product("Table","Furniture",7000,12));
        session.persist(new Product("Pen","Stationary",20,200));

        tx.commit();

        List<Product> asc = session.createQuery("from Product order by price asc", Product.class).list();
        asc.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        List<Product> desc = session.createQuery("from Product order by price desc", Product.class).list();
        desc.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        List<Product> qty = session.createQuery("from Product order by quantity desc", Product.class).list();
        qty.forEach(p -> System.out.println(p.getName()+" "+p.getQuantity()));

        List<Product> first3 = session.createQuery("from Product", Product.class)
                .setFirstResult(0)
                .setMaxResults(3)
                .list();
        first3.forEach(p -> System.out.println(p.getName()));

        List<Product> next3 = session.createQuery("from Product", Product.class)
                .setFirstResult(3)
                .setMaxResults(3)
                .list();
        next3.forEach(p -> System.out.println(p.getName()));

        Long total = session.createQuery("select count(*) from Product", Long.class).uniqueResult();
        System.out.println(total);

        Long available = session.createQuery("select count(*) from Product where quantity>0", Long.class).uniqueResult();
        System.out.println(available);

        Object[] price = (Object[]) session.createQuery("select min(price), max(price) from Product").uniqueResult();
        System.out.println(price[0]);
        System.out.println(price[1]);

        List<Object[]> group = session.createQuery("select description,count(*) from Product group by description").list();
        group.forEach(g -> System.out.println(g[0]+" "+g[1]));

        List<Product> range = session.createQuery("from Product where price between 1000 and 20000", Product.class).list();
        range.forEach(p -> System.out.println(p.getName()));

        session.createQuery("from Product where name like 'L%'", Product.class)
                .list()
                .forEach(p -> System.out.println(p.getName()));

        session.createQuery("from Product where name like '%r'", Product.class)
                .list()
                .forEach(p -> System.out.println(p.getName()));

        session.createQuery("from Product where name like '%top%'", Product.class)
                .list()
                .forEach(p -> System.out.println(p.getName()));

        session.createQuery("from Product where length(name)=5", Product.class)
                .list()
                .forEach(p -> System.out.println(p.getName()));

        session.close();
    }
}