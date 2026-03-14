package com.klu.entity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="students")
public class StudentEntity {
    @Id
    int id;
    String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public StudentEntity() {
        super();
    }
    @Override
    public String toString() {
        return "StudentEntity [id=" + id + ", name=" + name + "]";
    }

}
