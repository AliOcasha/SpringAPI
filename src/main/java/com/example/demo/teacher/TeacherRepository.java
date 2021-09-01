package com.example.demo.teacher;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

// Look into Student Equivalent
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>
{

    @Query("SELECT t FROM Teacher t WHERE t.mainsubject = ?1")
    List<Teacher> findTeachersbySubject(String mainsubject);

    @Query("SELECT t FROM Teacher t WHERE t.name = ?1")
    Optional<Teacher> findTeacherbyName(String name);

}
