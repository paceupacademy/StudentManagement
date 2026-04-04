package com.student.app.controller;

import com.student.app.model.*;
import com.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * StudentController:
 * ------------------
 * This class demonstrates how Spring Boot REST controllers work.
 *
 * Key Spring Boot Concepts:
 * -------------------------
 * 1. @RestController:
 *    - Combines @Controller and @ResponseBody.
 *    - Marks this class as a REST endpoint provider.
 *    - Methods return JSON/XML directly instead of rendering views.
 *
 * 2. @RequestMapping("/students"):
 *    - Sets the base URL for all endpoints in this controller.
 *    - Example: "/students/personal" maps to savePersonal().
 *
 * 3. Dependency Injection (@Autowired):
 *    - Spring Boot automatically injects StudentService bean.
 *    - Promotes loose coupling between controller and service layer.
 *
 * 4. Request Handling:
 *    - @GetMapping, @PostMapping map HTTP methods to Java methods.
 *    - @RequestBody binds JSON request payload to Java objects.
 *    - @RequestParam binds query/form parameters (e.g., file upload).
 *
 * 5. ResponseEntity:
 *    - Used to return HTTP responses with status codes and messages.
 *    - Spring Boot automatically serializes Java objects into JSON.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    /*
     * Service layer injected by Spring Boot 
     * Automatically injects required dependencies into class.
     * Eliminates the need for object creation
     */
	
    @Autowired
    private StudentService studentService;

    /**
     * GET endpoint: Fetch all students with full info.
     * URL: GET /students/
     */
    @GetMapping("/")
    public List<StudentFullInfoDTO> getAllStudents() {
        return studentService.getFullInfo();
    }

    /**
     * POST endpoint: Upload Excel file containing student data.
     * URL: POST /students/upload
     * Content-Type: multipart/form-data
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        studentService.saveStudentsFromExcel(file);
        return ResponseEntity.ok("Excel data uploaded successfully.");
    }

    /**
     * POST endpoint: Save student personal info.
     * URL: POST /students/personal
     * Body: JSON representing StudentPersonal
     */
    @PostMapping("/personal")
    public StudentPersonal savePersonal(@RequestBody StudentPersonal personal) {
        return studentService.saveStudentPersonal(personal);
    }

    /**
     * POST endpoint: Save student academic info.
     * URL: POST /students/academic
     */
    @PostMapping("/academic")
    public StudentAcademic saveAcademic(@RequestBody StudentAcademic academic) {
        return studentService.saveStudentAcademic(academic);
    }

    /**
     * POST endpoint: Save student attendance info.
     * URL: POST /students/attendance
     */
    @PostMapping("/attendance")
    public StudentAttendance saveAttendance(@RequestBody StudentAttendance attendance) {
        return studentService.saveStudentAttendance(attendance);
    }

    /**
     * POST endpoint: Save student sports info.
     * URL: POST /students/sports
     */
    @PostMapping("/sports")
    public StudentSports saveSports(@RequestBody StudentSports sports) {
        return studentService.saveStudentSports(sports);
    }

    /**
     * GET endpoint: Fetch all student personal records.
     * URL: GET /students/personal
     */
    @GetMapping("/personal")
    public List<StudentPersonal> getAllPersonal() {
        return studentService.getAllStudentPersonal();
    }

    /**
     * GET endpoint: Fetch one student's personal record by ID.
     * URL: GET /students/personal/{id}
     */
    @GetMapping("/personal/{id}")
    public StudentPersonal getPersonalById(@PathVariable int id) {
        return studentService.getStudentPersonalById(id);
    }
}

/*
Client (HTTP Request)
│
▼
┌───────────────────────────────┐
│ DispatcherServlet (Spring MVC)│
│ Central servlet for request   │
└───────────────┬───────────────┘
        │
        ▼
┌───────────────────────────────┐
│ HandlerMapping                │
│ Finds matching @RequestMapping│
└───────────────┬───────────────┘
        │
        ▼
┌────────────────────────────────────┐
│     StudentController              │
│ Method invoked (e.g. savePersonal) │
└────────────────┬───────────────────┘
        		 │
        		 ▼
┌────────────────────────────────┐
│ StudentService                 │
│ Business logic executed        │
└────────────────┬───────────────┘
        		 │
        		 ▼
┌───────────────────────────────┐
│ ResponseEntity / Object       │
│ Serialized to JSON            │
└───────────────────────────────┘
        		│
        		▼
	Client (HTTP Response)
*/