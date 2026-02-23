package com.student.app.repository;

import com.student.app.model.StudentPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPersonalRepository extends JpaRepository<StudentPersonal, Integer> {
}
