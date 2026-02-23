package com.student.app.controller;

import com.student.app.model.*;
import com.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.Serial;
import java.util.List;

@RestController
@RequestMapping("/students") //map https request to controller methods
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @GetMapping("/")
    public List<StudentFullInfoDTO> getAllStudents(){
    	return studentService.getFullInfo();
    }
    
    // Upload Excel file containing student data
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        studentService.saveStudentsFromExcel(file);
        return ResponseEntity.ok("Excel data uploaded successfully.");
    }

    // Add student personal info
    @PostMapping("/personal")
    public StudentPersonal savePersonal(@RequestBody StudentPersonal personal) {
        return studentService.saveStudentPersonal(personal);
    }

    // Add student academic info
    @PostMapping("/academic")
    public StudentAcademic saveAcademic(@RequestBody StudentAcademic academic) {
        return studentService.saveStudentAcademic(academic);
    }

    // Add student attendance info
    @PostMapping("/attendance")
    public StudentAttendance saveAttendance(@RequestBody StudentAttendance attendance) {
        return studentService.saveStudentAttendance(attendance);
    }

    // Add student sports info
    @PostMapping("/sports")
    public StudentSports saveSports(@RequestBody StudentSports sports) {
        return studentService.saveStudentSports(sports);
    }

    // Get all personal records
    @GetMapping("/personal")
    public List<StudentPersonal> getAllPersonal() {
        return studentService.getAllStudentPersonal();
    }

    // Get one student's personal record
    @GetMapping("/personal/{id}")
    public StudentPersonal getPersonalById(@PathVariable int id) {
    	return studentService.getStudentPersonalById(id);
    }
}
