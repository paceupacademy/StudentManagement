package com.paceup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.paceup.service.StudentServiceImplementation;

@SpringBootApplication
@ComponentScan(basePackages = "com.paceup")
@EnableJpaRepositories(basePackages = "com.paceup.repository")
public class StudentApp implements CommandLineRunner{
	
	@Autowired
	private StudentServiceImplementation service;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.createStudent(1, "Aishwarya");
		service.createStudent(2, "Anna");
		service.createStudent(3, "Danny");
		service.readStudent(1);
		service.readStudent(2);
		service.updateStudent(1, "Aishwarya Jadhav");
		service.deleteStudent(1);
		service.readStudent(2);
		service.readStudent(7);
	}
	
	
}