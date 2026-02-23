package com.student.app.model;

public class StudentFullInfoDTO {
    private int studentId;
    private String firstName;
    private String lastName;
    private String department;
    private double averageMarks;
    private int attendedClasses;
    private int totalClasses;
    private String sportName;
    private String level;

    public StudentFullInfoDTO(int studentId, String firstName, String lastName, String department,
                              double averageMarks, int attendedClasses, int totalClasses,
                              String sportName, String level) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.averageMarks = averageMarks;
        this.attendedClasses = attendedClasses;
        this.totalClasses = totalClasses;
        this.sportName = sportName;
        this.level = level;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getAverageMarks() { return averageMarks; }
    public void setAverageMarks(double averageMarks) { this.averageMarks = averageMarks; }
    public int getAttendedClasses() { return attendedClasses; }
    public void setAttendedClasses(int attendedClasses) { this.attendedClasses = attendedClasses; }
    public int getTotalClasses() { return totalClasses; }
    public void setTotalClasses(int totalClasses) { this.totalClasses = totalClasses; }
    public String getSportName() { return sportName; }
    public void setSportName(String sportName) { this.sportName = sportName; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
