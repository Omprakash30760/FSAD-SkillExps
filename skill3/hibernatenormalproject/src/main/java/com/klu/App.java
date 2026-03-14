package com.klu;
import org.hibernate.cfg.Configuration;

import com.klu.entity.StudentEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session s=factory.openSession();
        Transaction tx=s.beginTransaction();
        StudentEntity st=new StudentEntity();
        st.setId(111);
        st.setName("Spider Man");

        //insertion 
        s.save(st);
        
        //getting a record
        StudentEntity st1=s.get(StudentEntity.class,111);
        System.out.println("Student ID: " + st1.getId());
        System.out.println("Student Name: " + st1.getName());

        //updating a record
        st1.setName("Peter Parker");
        s.beginTransaction();
        s.update(st1);

        //deleting a record
        s.delete(st1);

        s.close();
        factory.close();
        System.out.println( "all operations completed!!" );
        

    }
}
