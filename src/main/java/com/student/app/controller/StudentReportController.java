package com.student.app.controller;

import com.student.app.model.StudentPersonal;
import com.student.app.model.StudentAcademic;
import com.student.app.model.StudentAttendance;
import com.student.app.model.StudentSports;
import com.student.app.repository.StudentPersonalRepository;
import com.student.app.repository.StudentAcademicRepository;
import com.student.app.repository.StudentAttendanceRepository;
import com.student.app.repository.StudentSportsRepository;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/wsdl")
public class StudentReportController {

    @Autowired
    private StudentPersonalRepository personalRepo;
    @Autowired
    private StudentAcademicRepository academicRepo;
    @Autowired
    private StudentAttendanceRepository attendanceRepo;
    @Autowired
    private StudentSportsRepository sportsRepo;

    @PostMapping(value = "/studentReport", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<StudentReportResponse> generateStudentReport(@RequestBody StudentReportRequest request) {
        int studentId = request.getStudentId();

        StudentPersonal personal = personalRepo.findById(studentId).orElse(null);
        StudentAcademic academic = academicRepo.findById(studentId).orElse(null);
        StudentAttendance attendance = attendanceRepo.findById(studentId).orElse(null);
        StudentSports sports = sportsRepo.findById(studentId).orElse(null);

        StringBuilder report = new StringBuilder();
        report.append("Student Report for ID: ").append(studentId).append("\n\n");
        if (personal != null) {
            report.append("Name: ").append(personal.getFirstName()).append(" ").append(personal.getLastName()).append("\n");
            report.append("DOB: ").append(personal.getDob()).append("\n");
            report.append("Contact: ").append(personal.getContactNumber()).append("\n\n");
        }
        if (academic != null) {
            report.append("Department: ").append(academic.getDepartment()).append("\n");
            report.append("Marks: ").append(academic.getAverageMarks()).append("\n\n");
        }
        if (attendance != null) {
            double percent = (attendance.getAttendedClasses() * 100.0) / attendance.getTotalClasses();
            report.append("Attendance: ").append(percent).append("%\n\n");
        }
        if (sports != null) {
            report.append("Sport: ").append(sports.getSportName()).append("\n");
            report.append("Level: ").append(sports.getLevel()).append("\n");
            report.append("Achievements: ").append(sports.getAchievements()).append("\n");
        }

        // Simulate PDF with plain text and encode in Base64
        String encodedPdf = Base64.getEncoder().encodeToString(report.toString().getBytes());

        StudentReportResponse response = new StudentReportResponse();
        response.setStudentId(studentId);
        response.setPdfData(encodedPdf);

        return ResponseEntity.ok(response);
    }

    @XmlRootElement
    public static class StudentReportRequest {
        private int studentId;
        private String format;

        public int getStudentId() { return studentId; }
        public void setStudentId(int studentId) { this.studentId = studentId; }

        public String getFormat() { return format; }
        public void setFormat(String format) { this.format = format; }
    }

    @XmlRootElement
    public static class StudentReportResponse {
        private int studentId;
        private String pdfData;

        public int getStudentId() { return studentId; }
        public void setStudentId(int studentId) { this.studentId = studentId; }

        public String getPdfData() { return pdfData; }
        public void setPdfData(String pdfData) { this.pdfData = pdfData; }
    }
}
