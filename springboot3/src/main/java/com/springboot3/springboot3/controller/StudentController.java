package com.springboot3.springboot3.controller;

import com.springboot3.springboot3.aop.LogExecution;
import com.springboot3.springboot3.model.Student;
import com.springboot3.springboot3.service.StudentService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAspectJAutoProxy
public class StudentController {



    @Autowired
    private StudentService studentService;


    @Autowired
    ObservationRegistry observationRegistry;



    @PostMapping("save")
    @LogExecution
    public Student addStudentcontroller(@RequestBody Student student){
        return studentService.addStudent(student);
    }


    @GetMapping("get")
    public List<Student> getAll(){
        return Observation.createNotStarted("getAllStudent", observationRegistry)
                .observe(()->studentService.getAll());
    }


    @GetMapping("get/{id}")
    public Student getById(@PathVariable(value = "id") String id){
        return studentService.getById(id);
    }

}
