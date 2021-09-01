package com.example.demo.student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Declaration for the class as a RestController and giving the Http Request a Path
@RestController@RequestMapping(path = "api/v1/student")
public class StudentController 
{
    // Using studentService
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;}

    // Declaring Functions with implementation in Student Service
    
    // Function for Get Request using a Path Variable
    @GetMapping(path = "{grade}")
	public List<Student> getStudents(@PathVariable("grade")Integer grade){
        return studentService.getStudents(grade);}

    // Function for Post Request using a Request Body taking the whole Object in
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);}
    
    // Function for Delete Request using two unrequired Parameters
    @DeleteMapping()
    public void deleteStudent(@RequestParam(required = false) Long id,
                              @RequestParam(required = false) Integer grade){
        studentService.deleteStudent(id, grade);}

    // Function for Put Request using a Path Variable and unrequired Parameters
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id, 
                              @RequestParam(required = false) String name, 
                              @RequestParam(required = false) String email){
        studentService.updateStudent(id, name, email);}

}