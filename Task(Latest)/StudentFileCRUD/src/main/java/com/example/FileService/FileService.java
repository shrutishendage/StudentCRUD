package com.example.FileService;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.entities.Student;

@Service
public class FileService {

    @Value("${file.path}")
    private String filePath; // The path to the file
    private int currentId = 0; // Current highest ID used

    public FileService() {
        // Default constructor
    }

    // Method to initialize the file if it does not exist
    private void initializeFile() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent()); // Create parent directories if they do not exist
            Files.createFile(path); // Create the file
        }
    }

    // Method to read all students from the file
    public List<Student> getAllStudents() throws IOException {
        initializeFile(); // Ensure file exists
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(parseStudent(line));
            }
        }
        return students;
    }

    // Method to add a student to the file
    public void addStudent(Student student) throws IOException {
        initializeFile(); // Ensure file exists
        student.setStudentId(++currentId); // Set the student ID
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(formatStudent(student));
            writer.newLine();
        }
    }

    // Helper method to parse a student from a file line
    private Student parseStudent(String line) {
        String[] parts = line.split(",");
        int studentId = Integer.parseInt(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        String email = parts[3];
        int age = Integer.parseInt(parts[4]);
        return new Student(studentId, firstName, lastName, email, age);
    }

    // Helper method to format a student as a file line
    private String formatStudent(Student student) {
        return student.getStudentId() + "," +
               student.getFirstName() + "," +
               student.getLastName() + "," +
               student.getEmail() + "," +
               student.getAge();
    }
    
 // Method to get a student by ID
    public Student getStudentById(int studentId) throws IOException {
        initializeFile(); // Ensure file exists
        List<Student> students = getAllStudents();
        return students.stream()
                       .filter(s -> s.getStudentId() == studentId)
                       .findFirst()
                       .orElse(null);
    }

    // Method to update a student record
    public void updateStudent(Student student) throws IOException {
        initializeFile(); // Ensure file exists
        List<Student> students = getAllStudents();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            boolean studentUpdated = false;
            for (Student s : students) {
                if (s.getStudentId() == student.getStudentId()) {
                    writer.write(formatStudent(student)); // Write updated student
                    studentUpdated = true;
                } else {
                    writer.write(formatStudent(s)); // Write existing student
                }
                writer.newLine();
            }
            if (!studentUpdated) {
                throw new RuntimeException("Student with ID " + student.getStudentId() + " not found.");
            }
        }
    }

    // Method to delete a student record
    public void deleteStudent(int studentId) throws IOException {
        initializeFile(); // Ensure file exists
        List<Student> students = getAllStudents();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            boolean studentDeleted = false;
            for (Student s : students) {
                if (s.getStudentId() != studentId) {
                    writer.write(formatStudent(s)); // Write student if not deleted
                    writer.newLine();
                } else {
                    studentDeleted = true;
                }
            }
            if (!studentDeleted) {
                throw new RuntimeException("Student with ID " + studentId + " not found.");
            }
        }
    }

}
