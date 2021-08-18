package com.example.demo.student;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.dob = ?1")
    Optional<Student> findStudentbyDoB(LocalDate dob);

    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    Optional<Student> findStudentbyName(String name);

    @Query("SELECT s FROM Student s WHERE s.grade = ?1")
    Optional<Student> findStudentbyGrade(Integer grade);

    public default boolean existbyEmail(String email){
        return findStudentByEmail(email) != null ? true : false;}

	public default boolean existbyName(String name){
        return findStudentbyName(name) != null ? true : false;}

	public default boolean existbyDoB(LocalDate dob){
        return findStudentbyDoB(dob) != null ? true : false;}

	public default boolean existbyGrade(Integer grade){
        return findStudentbyGrade(grade) != null ? true : false;}
}