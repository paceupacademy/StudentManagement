package com.student.app.model;

import jakarta.persistence.*; 
// jakarta.persistence provides JPA annotations used in Spring Boot.
// Spring Boot auto-configures Hibernate (default JPA provider) to interpret these annotations
// and map Java objects to database tables (ORM - Object Relational Mapping).

/**
 * ============================================================
 * SPRING BOOT + JPA ENTITY FLOW (Conceptual Flowchart)
 * ============================================================
 * 
 *   Application Startup (@SpringBootApplication)
 *        |
 *        v
 *   Component Scan -> Finds @Entity classes
 *        |
 *        v
 *   Hibernate initializes ORM mappings
 *        |
 *        v
 *   @Entity (StudentAttendance) mapped to @Table (student_attendance)
 *        |
 *        v
 *   Repository Layer (Spring Data JPA) provides CRUD methods
 *        |
 *        v
 *   Service Layer calls repository methods for business logic
 *        |
 *        v
 *   Controller Layer exposes REST endpoints
 *        |
 *        v
 *   Client (Frontend / API consumer) interacts with Controller
 *        |
 *        v
 *   Data persisted/retrieved from DB via Hibernate
 * 
 * ============================================================
 * This ensures seamless persistence and retrieval of attendance data
 * ============================================================
 */
/** @Entity marks this class as a JPA entity.
 * Hibernate will treat this class as a table representation.
 */
@Entity
@Table(name = "student_attendance")
/** 
 * @Table specifies the database table name explicitly.
 * Without this, Hibernate would default to the class name.
 */

public class StudentAttendance {

	@Id
	// @Id marks the primary key of the entity.
	// Every entity must have a unique identifier.
	private int studentId;

	// By default, fields without @Column map directly to columns with the same
	// name.
	private int totalClasses; // Represents total number of classes conducted.
	private int attendedClasses; // Represents number of classes attended by the student.

	// Default constructor is required by JPA for entity instantiation via
	// reflection.
	public StudentAttendance() {
	}

	// Parameterized constructor for convenience when creating objects manually.
	public StudentAttendance(int studentId, int totalClasses, int attendedClasses) {
		this.studentId = studentId;
		this.totalClasses = totalClasses;
		this.attendedClasses = attendedClasses;
	}

	/**
	 * ============================
	 * Getters and Setters
	 * ============================
	 *
	 * <p>These methods allow Spring and Hibernate to access and modify private fields.</p>
	 *
	 * <p>They are also used by frameworks like Jackson when serializing and deserializing JSON.</p>
	 */

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTotalClasses() {
		return totalClasses;
	}

	public void setTotalClasses(int totalClasses) {
		this.totalClasses = totalClasses;
	}

	public int getAttendedClasses() {
		return attendedClasses;
	}

	public void setAttendedClasses(int attendedClasses) {
		this.attendedClasses = attendedClasses;
	}
}
