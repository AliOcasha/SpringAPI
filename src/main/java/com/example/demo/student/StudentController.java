package com.example.demo.student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController 
{
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping(path = "{grade}")
	public List<Student> getStudents(@RequestParam(required = false)@PathVariable("grade")Integer grade)
	{
        return studentService.getStudents(grade);
	}

    @PostMapping
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId")Long id)
    {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id, 
                              @RequestParam(required = false) String name, 
                              @RequestParam(required = false) String email)
    {
        studentService.updateStudent(id, name, email);
    }

}