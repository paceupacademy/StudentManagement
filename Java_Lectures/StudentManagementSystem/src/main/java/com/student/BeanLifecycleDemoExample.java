package com.student;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * BeanLifecycleDemoExample:
 * -------------------------
 * Demonstrates the lifecycle of a Spring-managed bean.
 *
 * Key Lifecycle Stages:
 * ---------------------
 * 1. Constructor → Object creation.
 * 2. @PostConstruct → Initialization logic after dependency injection.
 * 3. afterPropertiesSet() → From InitializingBean, called once properties are set.
 * 4. @PreDestroy → Cleanup logic before bean is destroyed.
 * 5. destroy() → From DisposableBean, final cleanup.
 */
@Component
public class BeanLifecycleDemoExample implements InitializingBean, DisposableBean {

    // Step 1: Constructor is called when Spring creates the bean instance
    public BeanLifecycleDemoExample() {
        System.out.println("1️⃣ Constructor: Bean is created");
    }

    // Step 2: @PostConstruct runs after dependency injection is complete
    @PostConstruct
    public void postConstruct() {
        System.out.println("2️⃣ @PostConstruct: Initialization logic after injection");
    }

    // Step 3: afterPropertiesSet() is invoked by Spring once all properties are set
    @Override
    public void afterPropertiesSet() {
        System.out.println("3️⃣ afterPropertiesSet: from InitializingBean");
    }

    // Step 4: @PreDestroy runs before the bean is removed from the container
    @PreDestroy
    public void preDestroy() {
        System.out.println("4️⃣ @PreDestroy: Cleanup before bean is destroyed");
    }

    // Step 5: destroy() is invoked by Spring during bean destruction
    @Override
    public void destroy() {
        System.out.println("5️⃣ destroy(): from DisposableBean");
    }
}
