package com.student.app.repository;

import com.student.app.model.StudentAcademic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAcademicRepository extends JpaRepository<StudentAcademic, Integer> {
}
