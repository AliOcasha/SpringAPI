package com.example.demo.student;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    @Query("SELECT s FROM Student s WHERE s.email = ?1 AND s.dob = ?1 AND s.name = ?1 AND s.grade = ?1")
    Optional<Student> findStudentbyEverything(String email, LocalDate dob, String name, Integer grade);

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.dob = ?1")
    Optional<Student> findStudentbyDoB(LocalDate dob);

    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Optional<Student> findStudentbyName(String name);

    @Query("SELECT s FROM Student s WHERE s.grade = ?1")
    Optional<Student> findStudentbyGrade(Integer grade);
}