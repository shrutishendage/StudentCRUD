package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Student;
import com.example.repositories.StudentRepository;

@Service
public class StudentManagerImpl implements StudentManager
{
    @Autowired
    StudentRepository repository;

    @Override
    public void addStudent(Student student) {
        repository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @Override
    public void deleteStudent(int id) {
        repository.deleteById(id);
    }

    @Override
    public void updateStudent(Student student, int id) {
        System.out.println("Inside update method of service layer");
        repository.update(student.getFirstName(), student.getLastName(), student.getEmail(), student.getAge(), id);
    }

    @Override
    public Optional<Student> getStudent(int id) {
        return repository.findById(id);
    }
}
