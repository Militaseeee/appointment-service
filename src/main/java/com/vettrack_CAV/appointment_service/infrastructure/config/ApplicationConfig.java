package com.vettrack_CAV.appointment_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}