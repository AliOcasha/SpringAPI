package com.example.demo.student;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService 
{
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository)
	{
		this.studentRepository = studentRepository;
	}



    public List<Student> getStudents()
	{
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) 
	{
		Optional<Student> studentbyEmail = studentRepository.findStudentByEmail(student.getEmail());

		if (studentbyEmail.isPresent())
		{
			throw new IllegalStateException("Email Taken!");
		}
		studentRepository.save(student);
	}

    public void deleteStudent(Long id) 
	{
		boolean exists = studentRepository.existsById(id);

		if (!exists)
		{
			throw new IllegalStateException("Student with ID " + id + " does not exist!");
		}
		studentRepository.deleteById(id);
    }

	@Transactional()
	public void updateStudent(Long id, String name, String email) 
	{
		Student student = studentRepository.findById(id).
					    orElseThrow(() -> new IllegalStateException("Student with ID " + id + " does not exist!"));

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
		{
			student.setName(name);
		}
		if (email != null && email.length() > 0 && !Objects.equals(student.getName(), email))
		{
			Optional<Student> sOptional = studentRepository.findStudentByEmail(email);
			if (sOptional.isPresent())
			{
				throw new IllegalStateException("Email Taken!");
			}
			student.setEmail(email);
		}
	}
}