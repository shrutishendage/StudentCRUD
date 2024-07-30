package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> 
{
    @Modifying
    @Transactional
    @Query("update Student s set s.firstName = :firstName, s.lastName = :lastName, s.email = :email, s.age = :age where s.studentId = :id")
    void update(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("age") int age, @Param("id") int id);
}
