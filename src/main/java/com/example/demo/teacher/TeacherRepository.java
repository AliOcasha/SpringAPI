package com.example.demo.teacher;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>
{

    @Query("SELECT t FROM Teacher t WHERE t.mainsubject = ?1")
    List<Teacher> findTeachersbySubject(String mainsubject);

    @Query("SELECT t FROM Teacher t WHERE t.name = ?1")
    Optional<Teacher> findTeacherbyName(String name);

}
