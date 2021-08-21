package com.example.demo.student;

import java.time.*;
import java.util.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration
public class StudentConfig 
{
    @Bean
    CommandLineRunner commandLineRunnerS(StudentRepository repository)
    {
        List<Student> sL = new ArrayList<>();
        sL.add(new Student("Ali",LocalDate.of(1999, Month.DECEMBER, 8),"aliocasha@gmail.com",14));
        sL.add(new Student("Malek",LocalDate.of(2001, Month.JUNE, 12),"maleknaim@gmail.com",16));
        sL.add(new Student("Mustafa",LocalDate.of(2005, Month.SEPTEMBER, 5),"mustafaocasha@gmail.com",9));
        return args->{repository.saveAll(sL);};
    }
}