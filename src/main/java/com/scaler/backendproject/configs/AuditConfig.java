package com.scaler.backendproject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Return the username; replace this with logic to fetch authenticated user
        return () -> Optional.of("Purusharth Agarwal");
    }
}
