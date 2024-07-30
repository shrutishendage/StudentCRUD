package com.example.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FileService.FileService;
import com.example.entities.Student;

@Service
public class StudentManagerImpl implements StudentManager {

    @Autowired
    private FileService fileService;

    @Override
    public void addStudent(Student student) {
        try {
            fileService.addStudent(student);
        } catch (IOException e) {
            // Handle exception, e.g., log it
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getStudents() {
        try {
            return fileService.getAllStudents();
        } catch (IOException e) {
            // Handle exception, e.g., log it
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void deleteStudent(int id) {
        try {
            fileService.deleteStudent(id);
        } catch (IOException e) {
            // Handle exception, e.g., log it
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student, int id) {
        student.setStudentId(id);
        try {
            fileService.updateStudent(student);
        } catch (IOException e) {
            // Handle exception, e.g., log it
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> getStudent(int id) {
        try {
            return Optional.of(fileService.getStudentById(id));
        } catch (IOException e) {
            // Handle exception, e.g., log it
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
