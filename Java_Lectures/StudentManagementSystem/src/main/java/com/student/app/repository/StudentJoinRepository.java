package com.student.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.app.model.StudentPersonal;

@Repository
public interface StudentJoinRepository extends JpaRepository<StudentPersonal, Integer> {

    @Query(value = "SELECT p.student_id, p.first_name, p.last_name, a.department, a.average_marks, att.attended_classes, att.total_classes, s.sport_name, s.level " +
                   "FROM student_personal p " +
                   "JOIN student_academic a ON p.student_id = a.student_id " +
                   "JOIN student_attendance att ON p.student_id = att.student_id " +
                   "JOIN student_sports s ON p.student_id = s.student_id",
           nativeQuery = true)
    List<Object[]> fetchFullStudentInfoNative(); // Change name to avoid conflict
}