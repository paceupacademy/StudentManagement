package com.student.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_sports")
public class StudentSports {

    @Id
    private int studentId;

    private String sportName;
    private String level;
    private String achievements;

    public StudentSports() {}

    public StudentSports(int studentId, String sportName, String level, String achievements) {
        this.studentId = studentId;
        this.sportName = sportName;
        this.level = level;
        this.achievements = achievements;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getSportName() { return sportName; }
    public void setSportName(String sportName) { this.sportName = sportName; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getAchievements() { return achievements; }
    public void setAchievements(String achievements) { this.achievements = achievements; }
}
