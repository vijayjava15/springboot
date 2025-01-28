package com.springboot3.springboot3.service;

import com.springboot3.springboot3.model.Student;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    List<Student> studentList = new ArrayList<>();

    @Autowired
    ObservationRegistry observationRegistry;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public Student addStudent(Student student) {
        studentList.add(student);
        System.out.println(student);
        //eventPublisher.publishEvent();
        return student;
    }

    public List<Student> getAll() {
        return Observation
                .createNotStarted("getAll",observationRegistry).observe(()->studentList);
    }

    public Student getById(String id) {
       return studentList.stream()
                .filter(student -> student.id().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Student not found"));
    }
}
