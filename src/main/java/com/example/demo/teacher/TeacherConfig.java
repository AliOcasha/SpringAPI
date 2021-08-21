package com.example.demo.teacher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.time.*;
import java.util.*;

@Configuration
public class TeacherConfig 
{
    @Bean
    CommandLineRunner commandLineRunnerT(TeacherRepository repository)
    {
        List<Teacher> tL = new ArrayList<>();
        tL.add(new Teacher("Peter Bern", LocalDate.of(1976, Month.AUGUST,12), "Deutsch"));
        tL.add(new Teacher("Adolf Voltz", LocalDate.of(1973, Month.DECEMBER,1), "Mathe"));
        tL.add(new Teacher("Darius Kirschner", LocalDate.of(1981, Month.FEBRUARY,20), "Physik"));
        return args->{repository.saveAll(tL);};
    }    
}
