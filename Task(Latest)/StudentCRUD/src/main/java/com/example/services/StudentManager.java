package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.entities.Student;

public interface StudentManager
{
    void addStudent(Student student);
    List<Student> getStudents();
    void deleteStudent(int id);
    void updateStudent(Student student, int id);
    Optional<Student> getStudent(int id);
}
