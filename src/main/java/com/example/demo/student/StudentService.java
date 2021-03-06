package com.example.demo.student;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Declaring this class as Service
@Service
public class StudentService 
{
	// using the Student Repo
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;}

	// Defining Functions using student Repo Functions
    public List<Student> getStudents(Integer grade){
		return grade != 0 ? studentRepository.findStudentsbyGrade(grade) : studentRepository.findAll();}

	public void addNewStudent(Student student) 
	{
		Optional<Student> studentbyEmail = studentRepository.findStudentByEmail(student.getEmail());

		if (studentbyEmail.isPresent())
		{
			throw new IllegalStateException("Email Taken!");
		}
		studentRepository.save(student);
	}

    public void deleteStudent(Long id, Integer grade) 
	{
		boolean exists = grade != 0 ? studentRepository.existbyGrade(grade) : studentRepository.existsById(id);

		if (grade != 0)
		{
			if (!exists)
			{
				throw new IllegalStateException("Grade " + grade + " does not exist!");
			}
			studentRepository.deletebyGrade(grade);
		}
		else
		{
			if (!exists)
			{
				throw new IllegalStateException("Student with ID " + id + " does not exist!");
			}
			studentRepository.deleteById(id);
		}
    }

	// Defining a Function where Getter and Setters are allowed to use
	@Transactional()
	public void updateStudent(Long id, String name, String email) 
	{
		Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with ID " + id + " does not exist!"));

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
			student.setName(name);

		if (email != null && email.length() > 0 && !Objects.equals(student.getName(), email))
		{
			Optional<Student> sOptional = studentRepository.findStudentByEmail(email);

			if (sOptional.isPresent())
				throw new IllegalStateException("Email Taken!");

			student.setEmail(email);
		}
	}
}