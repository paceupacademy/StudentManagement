package com.student.app.model;

import jakarta.persistence.*;

/**
 * Student Entity:
 * ---------------
 * This class represents a database table in Spring Boot using JPA/Hibernate.
 *
 * Key Concepts:
 * -------------
 * 1. @Entity:
 *    - Marks this class as a JPA entity.
 *    - Each instance corresponds to a row in the database table.
 *
 * 2. @Table(name = "students"):
 *    - Maps this entity to the "students" table in the database.
 *    - Without @Table, JPA would default to using the class name.
 *
 * 3. @Id:
 *    - Marks the primary key field (id).
 *    - Required for every JPA entity.
 *
 * 4. @Column:
 *    - Maps Java fields to specific database columns.
 *    - Example: firstName → "first_name".
 *    - If omitted, JPA uses the field name by default.
 *
 * 5. Default Constructor:
 *    - Required by JPA for entity instantiation.
 *
 * 6. Parameterized Constructor:
 *    - Allows easy creation of Student objects in code.
 *
 * 7. Getters and Setters:
 *    - Provide access to private fields.
 *    - Used by JPA and application logic.
 *
 * Spring Boot + JPA Flow:
 * -----------------------
 * - Spring Boot auto-configures Hibernate as the JPA provider.
 * - Entities are scanned and mapped to tables.
 * - Repositories (e.g., StudentRepository) provide CRUD operations.
 */
@Entity
@Table(name = "students")
public class Student {

	@Id
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;
	private String department;
	private double marks;

	// Default constructor required by JPA
	public Student() {
	}

	// Parameterized constructor for convenience
	public Student(int id, String firstName, String lastName, String email, String department, double marks) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.marks = marks;
	}

	// Getters and setters (used by JPA and application logic)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
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
│  Entity Scan                   │
│ Finds @Entity classes (Student)│
└────────────────┬───────────────┘
        		 │
        		 ▼
┌───────────────────────────────┐
│ Table Mapping                 │
│ Student → "students" table    │
│ Fields → Columns              │
└───────────────┬───────────────┘
        		│
        		▼
┌────────────────────────────────┐
│  Repository Layer              │
│ StudentRepository provides CRUD│
└────────────────┬───────────────┘
        		 │
        		 ▼
┌───────────────────────────────┐
│ Application Logic             │
│ Save, update, query Student   │
└───────────────┬───────────────┘
        		│
        		▼
Database → Rows in "students" table
*/