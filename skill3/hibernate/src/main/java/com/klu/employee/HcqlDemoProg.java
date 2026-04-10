package com.klu.employee;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import com.klu.entity.employeeEntity;

public class HcqlDemoProg {

    public static void main(String[] args) {

        SessionFactory sf =
                new Configuration().configure("hibernate.cfg.xml")
                                   .buildSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();

        // -------------------- SELECT * --------------------
        CriteriaQuery<employeeEntity> cq = cb.createQuery(employeeEntity.class);
        Root<employeeEntity> r = cq.from(employeeEntity.class);
        cq.select(r);

        List<employeeEntity> list = s.createQuery(cq).getResultList();
        for (employeeEntity e : list) {
            System.out.println(e);
        }

        // -------------------- COUNT --------------------
        CriteriaQuery<Long> cq1 = cb.createQuery(Long.class);
        Root<employeeEntity> r1 = cq1.from(employeeEntity.class);
        cq1.select(cb.count(r1));
        Long count = s.createQuery(cq1).getSingleResult();
        System.out.println("No of records in table: " + count);

        // -------------------- MAX SALARY --------------------
        CriteriaQuery<Double> cq2 = cb.createQuery(Double.class);
        Root<employeeEntity> r2 = cq2.from(employeeEntity.class);
        cq2.select(cb.max(r2.get("salary")));
        Double maxSalary = s.createQuery(cq2).getSingleResult();
        System.out.println("Max Salary: " + maxSalary);

        // -------------------- MIN SALARY --------------------
        CriteriaQuery<Double> cq3 = cb.createQuery(Double.class);
        Root<employeeEntity> r3 = cq3.from(employeeEntity.class);
        cq3.select(cb.min(r3.get("salary")));
        Double minSalary = s.createQuery(cq3).getSingleResult();
System.out.println("Min Salary: " + minSalary);

        // -------------------- AVG SALARY --------------------
        CriteriaQuery<Double> cq4 = cb.createQuery(Double.class);
        Root<employeeEntity> r4 = cq4.from(employeeEntity.class);
        cq4.select(cb.avg(r4.get("salary")));
        Double avgSalary = s.createQuery(cq4).getSingleResult();
        System.out.println("Average Salary: " + avgSalary);

        // -------------------- MAX AGE --------------------
        CriteriaQuery<Integer> cq5 = cb.createQuery(Integer.class);
        Root<employeeEntity> r5 = cq5.from(employeeEntity.class);
        cq5.select(cb.max(r5.get("age")));
        Integer maxAge = s.createQuery(cq5).getSingleResult();
        System.out.println("Max Age: " + maxAge);

        // -------------------- MIN AGE --------------------
        CriteriaQuery<Integer> cq6 = cb.createQuery(Integer.class);
        Root<employeeEntity> r6 = cq6.from(employeeEntity.class);
        cq6.select(cb.min(r6.get("age")));
        Integer minAge = s.createQuery(cq6).getSingleResult();
        System.out.println("Min Age: " + minAge);

        // -------------------- AVG AGE --------------------
        CriteriaQuery<Double> cq7 = cb.createQuery(Double.class);
        Root<employeeEntity> r7 = cq7.from(employeeEntity.class);
        cq7.select(cb.avg(r7.get("age")));
        Double avgAge = s.createQuery(cq7).getSingleResult();
        System.out.println("Avg Age: " + avgAge);
        
        //--------salary > 50000----------------------
        
        CriteriaQuery<employeeEntity> cq8 = cb.createQuery(employeeEntity.class);
        Root<employeeEntity> r8 = cq8.from(employeeEntity.class);

        cq8.select(r8)
           .where(cb.greaterThan(r8.get("salary"), 50000.0));

        List<employeeEntity> highSalaryEmployees =
                s.createQuery(cq8).getResultList();

        System.out.println("Employees with salary > 50000:");
        for (employeeEntity e : highSalaryEmployees) {
            System.out.println(e);
        }

        
     /// --------------------  (SALARY BETWEEN 50000 AND 80000) --------------------
CriteriaQuery<String> cq9 = cb.createQuery(String.class);
Root<employeeEntity> r9 = cq9.from(employeeEntity.class);

cq9.select(r9.get("name"))
    .where(cb.between(r9.get("salary"), 50000.0, 80000.0));

List<String> names =
        s.createQuery(cq9).getResultList();

System.out.println("Employee names with salary between 50000 and 80000:");
for (String name : names) {
    System.out.println(name);
}

//-------- SALARY > 50000 AND NAME STARTS WITH 'R' --------
CriteriaQuery<String> cq10 = cb.createQuery(String.class);
Root<employeeEntity> r10 = cq10.from(employeeEntity.class);

cq10.select(r10.get("name"))
 .where(
     cb.and(
         cb.greaterThan(r10.get("salary"), 50000.0),
         cb.like(r10.get("name"), "R%")
     )
 );

List<String> rNames =
     s.createQuery(cq10).getResultList();

System.out.println("Employee names with salary > 50000 and name starts with R:");
for (String name : rNames) {
 System.out.println(name);
}

        s.close();
        sf.close();
    }
}
