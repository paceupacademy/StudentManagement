package com.paceup.model;

import jakarta.persistence.Entity; //Mark this class as a Entity
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	private int id; // primary key
	private String fName;

	public Student() {
	}

	public Student(int id, String fName) {
		this.id = id;
		this.fName = fName;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@Override
	public String toString() {
		return "Student {id=" + this.id + ", fName='" + this.fName + "'}";
	}
}