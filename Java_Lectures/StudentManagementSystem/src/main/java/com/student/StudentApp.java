package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MySpringBootApp:
 * ----------------
 * Entry point for a Spring Boot application.
 *
 * @SpringBootApplication is a meta-annotation that combines:
 *  1. @SpringBootConfiguration → Specialized form of @Configuration, marks this class as a source of bean definitions
 *     specifically for Spring Boot applications.
 *     - It is functionally equivalent to @Configuration but signals that this is the primary Boot configuration class.
 *
 *  2. @EnableAutoConfiguration → Enables Spring Boot’s auto-configuration based on classpath and properties.
 *     - Example: If spring-boot-starter-web is present, it configures Tomcat, DispatcherServlet, etc.
 *
 *  3. @ComponentScan → Scans the package and subpackages for Spring components.
 *     - Finds @Component, @Service, @Repository, @Controller classes and registers them as beans.
 *
 * Together, these annotations:
 * - Provide bean definitions.
 * - Automatically configure beans based on dependencies.
 * - Scan for components in the package.
 * - Deliver a ready-to-run application with minimal boilerplate.
 */
@SpringBootApplication
public class StudentApp {

    public static void main(String[] args) {
        // SpringApplication.run():
        // ------------------------
        // - Bootstraps the Spring context.
        // - Triggers auto-configuration.
        // - Starts embedded server (Tomcat/Jetty/Undertow) if web dependency is present.
        // - ApplicationContext is created, beans are instantiated, dependencies injected.
        SpringApplication.run(StudentApp.class, args);
    }
}
