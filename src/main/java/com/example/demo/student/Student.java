package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;
@Entity
@Table
public class Student 
{
    @Id
    @SequenceGenerator
    (
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue
    (
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )

    private Long id;
    private String name;
    private LocalDate dob;
    private String email;
    private Integer grade;
    
    @Transient
    private Integer age;
    
    public Student(){}
    public Student(Long id, String name, LocalDate dob, String email, Integer grade)
    {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.grade = grade;
    }
    public Student(String name, LocalDate dob, String email, Integer grade)
    {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.grade = grade;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return Period.between(this.getDob(), LocalDate.now()).getYears();
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Integer getGrade() {
        return grade;
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    
    @Override
    public String toString() {
        return "Student [age=" + age + ", dob=" + dob + ", email=" + email + ", grade=" + grade + ", id=" + id
                + ", name=" + name + "]";
    }
}