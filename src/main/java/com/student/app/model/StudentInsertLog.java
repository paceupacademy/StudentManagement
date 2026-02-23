package com.student.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StudentInsertLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int studentId;
    private String fullName;
    private LocalDateTime timestamp;

    public StudentInsertLog() {}

    public StudentInsertLog(int studentId, String fullName, LocalDateTime timestamp) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
