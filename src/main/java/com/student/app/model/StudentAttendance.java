package com.student.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_attendance")
public class StudentAttendance {

    @Id
    private int studentId;

    private int totalClasses;
    private int attendedClasses;

    public StudentAttendance() {}

    public StudentAttendance(int studentId, int totalClasses, int attendedClasses) {
        this.studentId = studentId;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getTotalClasses() { return totalClasses; }
    public void setTotalClasses(int totalClasses) { this.totalClasses = totalClasses; }

    public int getAttendedClasses() { return attendedClasses; }
    public void setAttendedClasses(int attendedClasses) { this.attendedClasses = attendedClasses; }
}
