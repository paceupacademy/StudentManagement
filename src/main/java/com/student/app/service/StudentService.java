package com.student.app.service;

import com.student.app.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    void saveStudentsFromExcel(MultipartFile file);

    StudentPersonal saveStudentPersonal(StudentPersonal personal);
    StudentAcademic saveStudentAcademic(StudentAcademic academic);
    StudentAttendance saveStudentAttendance(StudentAttendance attendance);
    StudentSports saveStudentSports(StudentSports sports);

    List<StudentPersonal> getAllStudentPersonal();
    StudentPersonal getStudentPersonalById(int id);
    List<StudentFullInfoDTO> getFullInfo();

	byte[] generateStudentReportPDF(int studentId);

	StudentAcademic getStudentAcademicById(int id);

	StudentAttendance getStudentAttendanceById(int id);

	StudentSports getStudentSportsById(int id);
	
	
}
