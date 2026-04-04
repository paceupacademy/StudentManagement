package com.student.app.listener;

import com.student.app.model.StudentInsertLog;
import com.student.app.model.StudentPersonal;
import com.student.app.repository.StudentInsertLogRepository;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * StudentPersonalListener:
 * ------------------------
 * This class demonstrates how **JPA Entity Listeners** integrate with Spring Boot.
 *
 * Key Concepts:
 * -------------
 * 1. @Component:
 *    - Marks this class as a Spring-managed bean.
 *    - Allows Spring to inject dependencies (like repositories).
 *
 * 2. Static Repository Injection:
 *    - JPA listeners are not Spring-managed by default.
 *    - To access Spring beans inside a listener, we inject the repository into a static field.
 *
 * 3. @Autowired init():
 *    - Spring calls this method during bean initialization.
 *    - Sets the static reference to StudentInsertLogRepository.
 *
 * 4. @PostPersist:
 *    - JPA lifecycle callback annotation.
 *    - Triggered automatically after a StudentPersonal entity is persisted (inserted into DB).
 *    - Useful for **auditing, logging, or triggering side effects**.
 *
 * 5. Logging Insert Events:
 *    - After a StudentPersonal record is saved, a StudentInsertLog entry is created.
 *    - Captures studentId, message, and timestamp.
 *    - Ensures every insert is tracked for auditing.
 */
@Component
public class StudentPersonalListener {

    // Static repository reference used inside JPA listener
    private static StudentInsertLogRepository logRepo;

    // Inject repository into static field
    @Autowired
    public void init(StudentInsertLogRepository repo) {
        StudentPersonalListener.logRepo = repo;
    }

    /**
     * @PostPersist → Runs after StudentPersonal entity is inserted.
     * - Creates a StudentInsertLog entry.
     * - Saves it using StudentInsertLogRepository.
     * - Provides automatic logging of insert events.
     */
    @PostPersist
    public void afterInsert(StudentPersonal student) {
        StudentInsertLog log = new StudentInsertLog(student.getStudentId(),student.getFirstName()+student.getLastName(),LocalDateTime.now());
        logRepo.save(log);
    }
}

/*
Save StudentPersonal Entity
        │
        ▼
┌───────────────────────────────┐
│ JPA EntityManager             │
│ Persists StudentPersonal      │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│ StudentPersonalListener       │
│ @PostPersist triggered        │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│ StudentInsertLogRepository    │
│ Save log entry with timestamp │
└───────────────┬───────────────┘
                │
                ▼
Database → StudentPersonal + StudentInsertLog
*/
