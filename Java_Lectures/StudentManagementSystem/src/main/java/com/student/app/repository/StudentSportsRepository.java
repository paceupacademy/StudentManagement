package com.student.app.repository;

import com.student.app.model.StudentSports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSportsRepository extends JpaRepository<StudentSports, Integer> {
}
