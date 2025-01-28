package com.springboot3.springboot3.controller;

import com.springboot3.springboot3.model.Student;
import com.springboot3.springboot3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class AppRunner implements CommandLineRunner {

    @Autowired
    StudentService studentService;
    @Override
    public void run(String... args) throws Exception {
        Student student = new Student("1L", "John Doe", "20");
        studentService.addStudent(student);
    }
}
