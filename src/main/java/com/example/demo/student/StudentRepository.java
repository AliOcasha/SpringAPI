package com.example.demo.student;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.grade = ?1")
    List<Student> findStudentsbyGrade(Integer grade);

    @Transactional@Modifying
    @Query("DELETE FROM Student s WHERE s.grade =:grade")
    void deletebyGrade(@Param("grade") Integer grade);

    public default boolean existbyEmail(String email){
        return findStudentByEmail(email) != null ? true : false;}

    public default boolean existbyGrade(Integer grade){
        return findStudentsbyGrade(grade) != null ? true : false;}
}