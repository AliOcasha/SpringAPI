package com.example.demo.teacher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController@RequestMapping(path = "api/v1/teacher")
public class TeacherController 
{
    private final TeacherService teacherService;    

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;}

    @GetMapping(path = "{mainsubject}")
    public List<Teacher> getTeachers(@PathVariable("mainsubject")String mainsubject){
        return teacherService.getTeachers(mainsubject);}

    @PostMapping
    public void registerNewTeacher(@RequestBody Teacher teacher){
        teacherService.addNewTeacher(teacher);}

    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Long id){
        teacherService.deleteTeacher(id);}

    @PutMapping(path = "{teacherId}")
    public void updateTeacher(@PathVariable("teacherId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String mainsubject){
        teacherService.updateTeacher(id, name, mainsubject);}
}
