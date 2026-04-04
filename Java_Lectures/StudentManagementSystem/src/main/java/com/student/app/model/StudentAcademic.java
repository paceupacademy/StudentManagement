package com.student.app.model;

import jakarta.persistence.*;

/**
 * StudentAcademic:
 * ----------------
 * Represents the academic details of a student in the database.
 *
 * Key Spring Boot + JPA Concepts:
 * -------------------------------
 * 1. @Entity:
 *    - Marks this class as a JPA entity.
 *    - Each instance corresponds to a row in the database table.
 *
 * 2. @Table(name = "student_academic"):
 *    - Maps this entity to the "student_academic" table.
 *    - Without @Table, JPA would default to using the class name.
 *
 * 3. Primary Key (@Id):
 *    - Every entity must have a primary key.
 *    - Typically, studentId would be used here to uniquely identify records.
 *
 * 4. Fields:
 *    - Each field in the class maps to a column in the table.
 *    - Example fields could include department, GPA, marks, etc.
 *
 * 5. Constructors:
 *    - Default constructor is required by JPA.
 *    - Parameterized constructor allows easy creation of objects in code.
 *
 * 6. Getters and Setters:
 *    - Provide access to private fields.
 *    - Used by JPA and application logic.
 *
 * Spring Boot Flow:
 * -----------------
 * - Spring Boot auto-configures Hibernate as the JPA provider.
 * - Entities are scanned and mapped to tables.
 * - Repositories (e.g., StudentAcademicRepository) provide CRUD operations.
 */
@Entity
@Table(name = "student_academic")
public class StudentAcademic {

	@Id
	private int studentId;

	private String department;
	private double averageMarks;

	public StudentAcademic() {
	}

	public StudentAcademic(int studentId, String department, double averageMarks) {
		this.studentId = studentId;
		this.department = department;
		this.averageMarks = averageMarks;
	}

	// Getters and Setters
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getAverageMarks() {
		return averageMarks;
	}

	public void setAverageMarks(double averageMarks) {
		this.averageMarks = averageMarks;
	}
}

/*
	Application Startup
			│
			▼
┌───────────────────────────────┐
│ Spring Boot Auto-Configuration│
│ Sets up JPA + Hibernate       │
└───────────────┬───────────────┘
        		│
        		▼
┌────────────────────────────────┐
│ Entity Scan                    │
│ Finds @Entity classes          │
│ (StudentAcademic, others)      │
└────────────────┬───────────────┘
        		 │
        		 ▼
┌──────────────────────────────────────┐
│ 		 Table Mapping                 │
│ StudentAcademic → "student_academic" │
│ 		 Fields → Columns              │
└───────────────────┬──────────────────┘
        			│
        			▼
┌───────────────────────────────┐
│ Repository Layer              │
│ StudentAcademicRepository CRUD│
└───────────────┬───────────────┘
        		│
        		▼
┌───────────────────────────────┐
│ Service Layer                 │
│ Business logic using entity   │
└───────────────┬───────────────┘
        		│
        		▼
Client (REST/SOAP Response) → Receives JSON/XML with academic info
*/