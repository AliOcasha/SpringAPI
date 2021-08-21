package com.example.demo.teacher;

import java.time.*;

import javax.persistence.*;

@Entity
@Table
public class Teacher 
{
    @Id
    @SequenceGenerator
    (
        name = "teacher_sequence",
        sequenceName = "teacher_sequence",
        allocationSize = 1
    )
    @GeneratedValue
    (
        strategy = GenerationType.SEQUENCE,
        generator = "teacher_sequence"
    )

    private long id;
    private String name;
    private LocalDate dob;
    private String mainsubject;
    
    @Transient
    private Integer age;
    
    @Transient
    private String email;
    
    public Teacher(){}
    
    public Teacher(long id, String name, LocalDate dob, String mainsubject) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.mainsubject = mainsubject;
    }

    public Teacher(String name, LocalDate dob, String mainsubject) {
        this.name = name;
        this.dob = dob;
        this.mainsubject = mainsubject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getMainsubject() {
        return mainsubject;
    }

    public void setMainsubject(String mainsubject) {
        this.mainsubject = mainsubject;
    }

    public Integer getAge() {
        return Period.between(this.getDob(), LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return name.toLowerCase().replaceAll(" ", "_") + "@niceschool.com";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher [age=" + age + ", dob=" + dob + ", email=" + email + ", id=" + id + ", mainsubject="
                + mainsubject + ", name=" + name + "]";
    }
}
