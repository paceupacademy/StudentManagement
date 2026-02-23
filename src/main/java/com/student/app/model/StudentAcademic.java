package com.student.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_academic")
public class StudentAcademic {

    @Id
    private int studentId;

    private String department;
    private double averageMarks;

    public StudentAcademic() {}

    public StudentAcademic(int studentId, String department, double averageMarks) {
        this.studentId = studentId;
        this.department = department;
        this.averageMarks = averageMarks;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getAverageMarks() { return averageMarks; }
    public void setAverageMarks(double averageMarks) { this.averageMarks = averageMarks; }
}
