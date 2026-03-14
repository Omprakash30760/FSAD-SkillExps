package com.klu.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.klu.entity.employeeEntity;

public class App {
    public static void main(String[] args) {

        SessionFactory sf =
                new Configuration().configure("hibernate.cfg.xml")
                                   .buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        // To insert employee details
        employeeEntity ee1 = new employeeEntity(101, "xyz", 29, 65000.45);
        employeeEntity ee2 = new employeeEntity(201, "Prajith", 67, 650000.78);
        employeeEntity ee3 = new employeeEntity(301, "Dheerya", 27, 78000.95);
        employeeEntity ee4 = new employeeEntity(401, "Hiccup", 45, 55000.45);

        s.save(ee1);
        s.save(ee2);
        s.save(ee3);
        s.save(ee4);

        // To count the records
        Long count = s.createQuery(
                "select count(emp_id) from employeeEntity",
                Long.class
        ).getSingleResult();

        System.out.println("No of records existed in the employee table are: " + count);

        // Sum of salaries
        Double sSum = s.createQuery(
                "select sum(salary) from employeeEntity",
                Double.class
        ).getSingleResult();
        System.out.println("Total salary of the employees: " + sSum);

        // Average of salaries
        Double AvS = s.createQuery(
                "select avg(salary) from employeeEntity",
                Double.class
        ).getSingleResult();
        System.out.println("AVERAGE salary of the employees: " + AvS);

        // Average of age
        Double AvgAge = s.createQuery(
                "select avg(age) from employeeEntity",
                Double.class
        ).getSingleResult();
        System.out.println("AVERAGE age of the employees: " + AvgAge);

        // Order By age ASC
        List<employeeEntity> records =
                s.createQuery(
                        "from employeeEntity order by age asc",
                        employeeEntity.class
                ).getResultList();

        for (employeeEntity e : records) {
            System.out.println(
                "Employee Id: " + e.getEmp_id() +
                " Employee Name: " + e.getName() +
                " Employee Age: " + e.getAge() +
                " Employee Salary: " + e.getSalary()
            );
        }

        // Commit & close
        tx.commit();
        s.close();
        sf.close();
    }
}
