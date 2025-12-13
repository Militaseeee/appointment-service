// Contenido corregido para SecurityConfig.java
package com.vettrack_CAV.appointment_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Whitelist para permitir acceso a Swagger/OpenAPI
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/webjars/**",
            // Permite login y error para evitar redirecciones infinitas
            "/login",
            "/error"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acceso a las rutas de Swagger (y login)
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        // Cualquier otra cosa debe ser autenticada
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        // Redirigir a Swagger después del login exitoso
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                );

        return http.build();
    }
}