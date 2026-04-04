package com.student.app.endpoint;

import com.student.app.generated.soap.StudentReportRequest;
import com.student.app.generated.soap.StudentReportResponse;
import com.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.Base64;
import java.util.Objects;

/**
 * StudentReportEndpoint:
 * ----------------------
 * Demonstrates how Spring Boot integrates with Spring Web Services (Spring-WS) to expose SOAP endpoints.
 *
 * Key Concepts:
 * -------------
 * 1. @Endpoint:
 *    - Marks this class as a SOAP endpoint.
 *    - Equivalent to @RestController in REST, but for SOAP.
 *
 * 2. @PayloadRoot:
 *    - Maps incoming SOAP messages to a specific method.
 *    - Uses namespace + localPart (root element of the SOAP request).
 *
 * 3. @RequestPayload:
 *    - Binds the SOAP request body to a Java object (StudentReportRequest).
 *
 * 4. @ResponsePayload:
 *    - Ensures the method return value is serialized back into a SOAP response.
 *
 * 5. Service Layer Integration:
 *    - StudentService is injected via @Autowired.
 *    - Business logic (PDF generation) is delegated to the service layer.
 *
 * 6. Base64 Encoding:
 *    - PDF bytes are encoded into Base64 string for transport in SOAP response.
 */
@Endpoint
public class StudentReportEndpoint {

    // Namespace URI defines unique identifier for SOAP service
    private static final String NAMESPACE_URI = "http://student.com/report";

    private final StudentService studentService;

    @Autowired
    public StudentReportEndpoint(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * SOAP operation: getStudentReport
     * - Triggered when SOAP request has root element <StudentReportRequest>
     * - Namespace must match NAMESPACE_URI
     * - Returns StudentReportResponse with Base64 encoded PDF
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentReportRequest")
    @ResponsePayload
    public StudentReportResponse getStudentReport(@RequestPayload StudentReportRequest request) {
        int studentId = request.getStudentId();

        StudentReportResponse response = new StudentReportResponse();

        // Validate input
        if (Objects.isNull(studentId)) {
            response.setPdfBase64("ERROR: studentId is null.");
            return response;
        }

        // Delegate to service layer
        byte[] pdfBytes = studentService.generateStudentReportPDF(studentId);

        // Encode PDF as Base64 string
        String base64EncodedPdf = Base64.getEncoder().encodeToString(pdfBytes);

        // Build SOAP response
        response.setPdfBase64(base64EncodedPdf);
        return response;
    }
}

/*
SOAP Client Request (XML)
			│
			▼
┌───────────────────────────────┐
│ MessageDispatcherServlet      │
│ Central SOAP controller       │
└───────────────┬───────────────┘
        		│
        		▼
┌────────────────────────────────┐
│ StudentReportEndpoint          │
│ @PayloadRoot matches request   │
│ Method invoked                 │
└────────────────┬───────────────┘
        		 │
        		 ▼
┌────────────────────────────────┐
│ StudentService                 │
│ Business logic: generate PDF   │
└───────────────┬────────────────┘
        		│
        		▼
┌───────────────────────────────┐
│ Response Object (SOAP)        │
│ PDF encoded as Base64 string  │
└───────────────┬───────────────┘
        		│
        		▼
	SOAP Client Response (XML)
*/