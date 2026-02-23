package com.student.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_personal")
public class StudentPersonal {

    @Id
    private int studentId;

    private String firstName;
    private String lastName;
    private String dob;
    private String contactNumber;

    public StudentPersonal() {}

    public StudentPersonal(int studentId, String firstName, String lastName, String dob, String contactNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
