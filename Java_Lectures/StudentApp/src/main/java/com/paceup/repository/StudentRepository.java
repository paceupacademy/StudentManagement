package com.paceup.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paceup.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
