package com.example.demo.teacher;

import java.nio.channels.IllegalSelectorException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;}

    public List<Teacher> getTeachers(String mainsubject) {
        return mainsubject != null ? teacherRepository.findTeachersbySubject(mainsubject) : teacherRepository.findAll();}

    public void addNewTeacher(Teacher teacher) 
    {
        Optional<Teacher> teacherbyEmail = teacherRepository.findTeacherbyEmail(teacher.getEmail());

        if (teacherbyEmail.isPresent())
        {
            throw new IllegalStateException("Email Taken!");
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
        teacherRepository.deletebyId(id);
    }

    @Transactional
    public void updateTeacher(Long id, String name, String mainsubject) 
    {
        Teacher teacher = teacherRepository.findbyId(id).orElseThrow(() -> new IllegalStateException("Teacher with ID " + id + " does not exist!"));
        
        if (name != null && name.length() > 0 && !Objects.equals(teacher.getName(), name))
			teacher.setName(name);

        if (mainsubject != null && mainsubject.length() > 0 && !Objects.equals(teacher.getName(), mainsubject))
		{
			Optional<Teacher> tOptional = teacherRepository.findTeacherbyEmail(mainsubject);

			if (tOptional.isPresent())
				throw new IllegalStateException("Email Taken!");

			teacher.setEmail(mainsubject);
		}
    }
    
}
