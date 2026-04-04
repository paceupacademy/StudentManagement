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

/**
 * StudentReportController:
 * ------------------------
 * Demonstrates how Spring Boot integrates REST + SOAP-style XML endpoints.
 *
 * Key Spring Boot Concepts:
 * -------------------------
 * 1. @RestController:
 *    - Combines @Controller + @ResponseBody.
 *    - Marks this class as a REST controller.
 *    - Methods return objects directly, automatically serialized (JSON/XML).
 *
 * 2. @RequestMapping("/wsdl"):
 *    - Base URL for all endpoints in this controller.
 *    - Example: POST /wsdl/studentReport
 *
 * 3. Dependency Injection (@Autowired):
 *    - Spring Boot injects repository beans automatically.
 *    - Promotes loose coupling between controller and persistence layer.
 *
 * 4. Content Negotiation:
 *    - @PostMapping with consumes/produces = MediaType.APPLICATION_XML_VALUE.
 *    - Ensures request/response are handled as XML.
 *
 * 5. ResponseEntity:
 *    - Wraps response object with HTTP status code.
 *    - Provides flexibility in returning success/error responses.
 */
@RestController
@RequestMapping("/wsdl")
public class StudentReportController {

    // Repository beans injected by Spring Boot
    @Autowired
    private StudentPersonalRepository personalRepo;
    @Autowired
    private StudentAcademicRepository academicRepo;
    @Autowired
    private StudentAttendanceRepository attendanceRepo;
    @Autowired
    private StudentSportsRepository sportsRepo;

    /**
     * POST endpoint: Generate student report in XML.
     * URL: POST /wsdl/studentReport
     * Request: XML payload containing studentId
     * Response: XML payload containing Base64 encoded "PDF" data
     */
    @PostMapping(value = "/studentReport", 
                 consumes = MediaType.APPLICATION_XML_VALUE, 
                 produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<StudentReportResponse> generateStudentReport(@RequestBody StudentReportRequest request) {
        int studentId = request.getStudentId();

        // Fetch student data from repositories
        StudentPersonal personal = personalRepo.findById(studentId).orElse(null);
        StudentAcademic academic = academicRepo.findById(studentId).orElse(null);
        StudentAttendance attendance = attendanceRepo.findById(studentId).orElse(null);
        StudentSports sports = sportsRepo.findById(studentId).orElse(null);

        // Build report text
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

        // Simulate PDF with plain text encoded in Base64
        String encodedPdf = Base64.getEncoder().encodeToString(report.toString().getBytes());

        // Build response object
        StudentReportResponse response = new StudentReportResponse();
        response.setStudentId(studentId);
        response.setPdfData(encodedPdf);

        return ResponseEntity.ok(response);
    }

    /**
     * Request DTO for student report.
     * - Annotated with @XmlRootElement to support XML serialization/deserialization.
     */
    @XmlRootElement
    public static class StudentReportRequest {
        private int studentId;
        private String format;

        public int getStudentId() { return studentId; }
        public void setStudentId(int studentId) { this.studentId = studentId; }

        public String getFormat() { return format; }
        public void setFormat(String format) { this.format = format; }
    }

    /**
     * Response DTO for student report.
     * - Contains studentId and Base64 encoded PDF data.
     * - Returned as XML because of produces = APPLICATION_XML_VALUE.
     */
    public static class StudentReportResponse {
        private int studentId;
        private String pdfData;

        public int getStudentId() { return studentId; }
        public void setStudentId(int studentId) { this.studentId = studentId; }

        public String getPdfData() { return pdfData; }
        public void setPdfData(String pdfData) { this.pdfData = pdfData; }
    }
}

/*
Client (SOAP/XML Request)
			│
			▼
┌───────────────────────────────┐
│ DispatcherServlet (Spring MVC)│
│ Central servlet for requests  │
└───────────────┬───────────────┘
        		│
        		▼
┌───────────────────────────────┐
│ StudentReportController        │
│ @PostMapping("/studentReport") │
│ Method invoked                 │
└───────────────┬───────────────┘
        		│
        		▼
┌───────────────────────────────┐
│ Repositories (JPA)            │
│ Fetch student data from DB    │
└───────────────┬───────────────┘
        		│
        		▼
┌────────────────────────────────┐
│ Build Report String            │
│ Encode as Base64 "PDF"         │
└────────────────┬───────────────┘
        		 │
        		 ▼
┌───────────────────────────────┐
│ ResponseEntity<StudentReport> │
│ Serialized to XML             │
└───────────────────────────────┘
        		│
        		▼
	Client (SOAP/XML Response)
*/