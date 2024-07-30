package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Student;
import com.example.services.StudentManager;

@RestController
public class StudentController {

    @Autowired
    StudentManager manager;

    @GetMapping(value = "api/students")
    public List<Student> getStudents() {
        return manager.getStudents();
    }

    @GetMapping(value = "api/studentsById/{studentId}")
    public Optional<Student> getStudentById(@PathVariable int studentId) {
        return manager.getStudent(studentId);
    }

    @DeleteMapping(value = "api/students/{studentId}")
    public void deleteStudent(@PathVariable int studentId) {
        manager.deleteStudent(studentId);
    }

    @PutMapping(value = "api/students/{studentId}")
    public void updateStudent(@RequestBody Student student, @PathVariable int studentId) {
        System.out.println("Inside updateStudent of controller");
        manager.updateStudent(student, studentId);
    }

    @PostMapping(value = "api/students")
    public void addStudent(@RequestBody Student student) {
        System.out.println("addStudent called");
        manager.addStudent(student);
    }
}
