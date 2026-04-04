package com.student.app.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.context.ApplicationContext;

/**
 * WebServiceConfig:
 * -----------------
 * Configures SOAP Web Services in a Spring Boot application.
 *
 * Key Components:
 * ---------------
 * 1. MessageDispatcherServlet:
 *    - Central servlet that handles incoming SOAP requests.
 *    - Linked with Spring ApplicationContext.
 *    - Transforms WSDL locations so clients get correct URLs.
 *
 * 2. DefaultWsdl11Definition:
 *    - Generates WSDL 1.1 definition automatically.
 *    - Defines PortType, Namespace, Endpoint URI, and Schema.
 *
 * 3. XsdSchema:
 *    - Loads XSD file from resources.
 *    - Defines XML structure for request/response messages.
 */
@EnableWs
@Configuration
public class WebServiceConfig {

    /**
     * Registers MessageDispatcherServlet to handle SOAP requests.
     * - Maps all SOAP calls to /ws/*
     * - Integrates with Spring ApplicationContext.
     * - Ensures WSDL URLs are correctly rewritten for clients.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Defines WSDL 1.1 configuration.
     * - PortTypeName: Logical name for client port.
     * - LocationUri: Endpoint where SOAP service is available (/ws).
     * - TargetNamespace: Unique identifier for the service.
     * - Schema: XML structure defined by XSD.
     *
     * WSDL will be available at: /ws/studentPdf.wsdl
     */
    @Bean(name = "studentPdf")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema studentSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("StudentPdfPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://student.com/pdf");
        wsdl11Definition.setSchema(studentSchema);
        return wsdl11Definition;
    }
    
    /**
     * Loads XSD schema from classpath.
     * - Defines structure of XML request and response.
     * - Ensures SOAP messages conform to schema.
     */
    @Bean
    public XsdSchema studentSchema() {
        return new SimpleXsdSchema(new ClassPathResource("wsdl/student-report.xsd"));
    }
}

/*
Client (SOAP Request)
        │
        ▼
┌───────────────────────────────┐
│ MessageDispatcherServlet      │
│ - Central SOAP controller     │
│ - Linked to Spring Context    │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│ DefaultWsdl11Definition       │
│ - Generates WSDL at /ws/*.wsdl│
│ - Defines PortType, Namespace │
│ - Uses XSD Schema             │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│ XsdSchema                     │
│ - Defines XML structure       │
│ - Validates request/response  │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│ Spring Service Implementation │
│ - Handles business logic      │
│ - Returns SOAP Response       │
└───────────────────────────────┘

*/