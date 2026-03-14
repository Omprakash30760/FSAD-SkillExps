package com.klu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="employees")
public class employeeEntity {

    @Id
    int emp_id;
    String name;
    int age;
    Double salary;

    public employeeEntity() {
        super();
    }

    // 🔴 THIS WAS THE MAIN PROBLEM — NOW FIXED
    public employeeEntity(int emp_id, String name, int age, double salary) {
        this.emp_id = emp_id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "employeeEntity [emp_id=" + emp_id +
               ", name=" + name +
               ", age=" + age +
               ", salary=" + salary + "]";
    }
}
