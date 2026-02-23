package com.student.app.endpoint;

import com.student.app.generated.soap.StudentReportRequest;
import com.student.app.generated.soap.StudentReportResponse;
import com.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;


import java.util.Base64;
import java.util.Objects;

@Endpoint
public class StudentReportEndpoint {

    private static final String NAMESPACE_URI = "http://student.com/report";

    private final StudentService studentService;

    @Autowired
    public StudentReportEndpoint(StudentService studentService) {
        this.studentService = studentService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentReportRequest")
    @ResponsePayload
    public StudentReportResponse getStudentReport(@RequestPayload StudentReportRequest request) {
        int studentId = request.getStudentId();

        StudentReportResponse response = new StudentReportResponse();

        if (Objects.isNull(studentId)) {
            response.setPdfBase64("ERROR: studentId is null.");
            return response;
        }

        byte[] pdfBytes = studentService.generateStudentReportPDF(studentId);
        String base64EncodedPdf = Base64.getEncoder().encodeToString(pdfBytes);

        response.setPdfBase64(base64EncodedPdf);
        return response;
    }
}
