package com.student;

import org.springframework.beans.factory.DisposableBean;   // Provides destroy() for cleanup
import org.springframework.beans.factory.InitializingBean; // Provides afterPropertiesSet() for initialization
import org.springframework.stereotype.Component;       // Marks class as Spring-managed bean

import jakarta.annotation.PostConstruct;  // Runs after dependency injection
import jakarta.annotation.PreDestroy;     // Runs before bean destruction

/**
 * BeanLifecycleDemoExample
 * -------------------------
 * Demonstrates the lifecycle of a Spring-managed bean.
 *
 * Lifecycle Stages:
 * -----------------
 * 1. Constructor → Object creation.
 * 2. @PostConstruct → Initialization logic after dependency injection.
 * 3. afterPropertiesSet() → From InitializingBean, called once properties are set.
 * 4. @PreDestroy → Cleanup logic before bean is destroyed.
 * 5. destroy() → From DisposableBean, final cleanup.
 */
@Component
public class BeanLifecycleDemoExample implements InitializingBean, DisposableBean {

    /**
     * Step 1: Constructor
     * -------------------
     * Invoked when Spring creates the bean instance.
     * Used for basic object creation logic.
     */
    public BeanLifecycleDemoExample() {
        System.out.println("1️⃣ Constructor: Bean is created");
    }

    /**
     * Step 2: @PostConstruct
     * ----------------------
     * Runs after dependency injection is complete.
     * Ideal for initialization tasks like opening resources.
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("2️⃣ @PostConstruct: Initialization logic after injection");
    }

    /**
     * Step 3: afterPropertiesSet()
     * ----------------------------
     * From InitializingBean interface.
     * Called once all properties are set by Spring.
     * Useful for validation or custom initialization.
     */
    @Override
    public void afterPropertiesSet() {
        System.out.println("3️⃣ afterPropertiesSet: from InitializingBean");
    }

    /**
     * Step 4: @PreDestroy
     * -------------------
     * Runs before the bean is removed from the container.
     * Ideal for cleanup tasks like closing resources.
     */
    @PreDestroy
    public void preDestroy() {
        System.out.println("4️⃣ @PreDestroy: Cleanup before bean is destroyed");
    }

    /**
     * Step 5: destroy()
     * -----------------
     * From DisposableBean interface.
     * Invoked during bean destruction for final cleanup.
     */
    @Override
    public void destroy() {
        System.out.println("5️⃣ destroy: from DisposableBean");
    }
}
