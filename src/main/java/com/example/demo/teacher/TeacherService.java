package com.example.demo.teacher;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Look into Student Equivalent

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;}

    public List<Teacher> getTeachers(String mainsubject) {
        System.out.println(mainsubject);
        return !mainsubject.equals("none") ? teacherRepository.findTeachersbySubject(mainsubject) : teacherRepository.findAll();}

    public void addNewTeacher(Teacher teacher) 
    {
        Optional<Teacher> teacherbyEmail = teacherRepository.findTeacherbyName(teacher.getName());

        if (teacherbyEmail.isPresent())
        {
            throw new IllegalStateException("Teacher already in System!");
        }
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) 
    {
        boolean exists = teacherRepository.existsById(id);

        if (!exists)
        {
            throw new IllegalStateException("Teacher with ID " + id + " does not exist!");
        }
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Long id, String name, String mainsubject) 
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new IllegalStateException("Teacher with ID " + id + " does not exist!"));
        
        if (name != null && name.length() > 0 && !Objects.equals(teacher.getName(), name))
			teacher.setName(name);

        if (mainsubject != null && mainsubject.length() > 0 && !Objects.equals(teacher.getName(), mainsubject))
		{
			Optional<Teacher> tOptional = teacherRepository.findTeacherbyName(mainsubject);

			if (tOptional.isPresent())
				throw new IllegalStateException("Teacher already in System!");

			teacher.setMainsubject(mainsubject);
		}
    }
    
}
