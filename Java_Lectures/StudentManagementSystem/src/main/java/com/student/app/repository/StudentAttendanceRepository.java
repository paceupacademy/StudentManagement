package com.student.app.repository;

import com.student.app.model.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {
}
