package com.paceup.service;

import com.paceup.model.Student;
import com.paceup.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * This class uses Hibernate 'Session' and 'Transaction' for managing students
 */

@Service
public class StudentServiceImplementation {

    @Autowired
    private StudentRepository studentRepository; // Dependency Injection

    // CREATE student
    public void createStudent(int id, String name) { //Create new student record
        Student student = new Student(id, name);
        studentRepository.save(student); //to create or update record
        System.out.println("Student added successfully");
    }

    // READ student
    public void readStudent(int id) { //Retrieve student record
        Optional<Student> studentOpt = studentRepository.findById(id); //to retrieve a student by ID
        if (studentOpt.isPresent()) {
            System.out.println("Student details: " + studentOpt.get());
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    // UPDATE student
    public void updateStudent(int id, String newName) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setfName(newName); // Assuming setfName is a setter
            studentRepository.save(student);
            System.out.println("Student updated successfully");
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    // DELETE student
    public void deleteStudent(int id) {
        if (studentRepository.existsById(id)) { 
            studentRepository.deleteById(id); // to delete by using 
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }
}