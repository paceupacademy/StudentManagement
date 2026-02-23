package com.student;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class BeanLifecycleDemoExample implements InitializingBean, DisposableBean {

    public BeanLifecycleDemoExample() {
        System.out.println("1️⃣ Constructor: Bean is created");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("2️⃣ @PostConstruct: Initialization logic after injection");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("3️⃣ afterPropertiesSet: from InitializingBean");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("4️⃣ @PreDestroy: Cleanup before bean is destroyed");
    }

    @Override
    public void destroy() {
        System.out.println("5️⃣ destroy: from DisposableBean");
    }
}