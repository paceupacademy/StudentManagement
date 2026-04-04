package com.student.app.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

/**
 * WebSecurityConfig:
 * ------------------
 * Central configuration class for:
 * 1. User authentication (in-memory users).
 * 2. HTTP security rules (authorization, CSRF, login).
 * 3. Password encoding strategy.
 * 4. Multipart file upload limits.
 *
 * Annotations:
 * - @Configuration → Marks this class as a source of bean definitions.
 * - @EnableWebSecurity → Enables Spring Security’s web security support.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Defines in-memory users for authentication.
     * - "aishwarya" with role USER
     * - "admin" with role ADMIN
     * 
     * InMemoryUserDetailsManager stores these users in memory.
     * Useful for demos and testing; production apps should use a database or external identity provider.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("aishwarya")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * Configures HTTP security rules.
     * - CSRF disabled (for simplicity; not recommended in production).
     * - Authorization rules:
     *   → "/students/upload" and "/login" are open to all.
     *   → "/students/**" requires USER or ADMIN role.
     *   → Any other request requires authentication.
     * - httpBasic() enables basic authentication (username/password in HTTP headers).
     * - http.build() finalizes the SecurityFilterChain used at startup.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        http.csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/students/upload", "/login").permitAll()
                .requestMatchers("/students/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }

    /**
     * Defines password encoding strategy.
     * - NoOpPasswordEncoder stores passwords in plain text.
     * - Only for demo purposes; in production use BCryptPasswordEncoder or another secure encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    /**
     * Configures multipart file upload limits.
     * - Max file size: 10 MB
     * - Max request size: 10 MB
     * MultipartConfigFactory builds the configuration, then createMultipartConfig() finalizes it.
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        factory.setMaxRequestSize(DataSize.ofMegabytes(10));
        return factory.createMultipartConfig();
    }
}
