package com.scaler.backendproject.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
