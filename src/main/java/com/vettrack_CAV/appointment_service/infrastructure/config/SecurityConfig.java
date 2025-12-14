package com.vettrack_CAV.appointment_service.infrastructure.config;

import com.vettrack_CAV.appointment_service.infrastructure.security.JwtAuthenticationFilter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    // Rutas públicas (Swagger y Autenticación)
    private static final String[] PUBLIC_URLS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/webjars/**",
            "/auth/**",
            "/api/auth/**",
            "/actuator/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Permitimos las rutas públicas definidas arriba
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        // 2. Cualquier otra ruta requiere token
                        .anyRequest().authenticated()
                )
                // 3. Importante: Sin estado (Stateless) porque usamos JWT, no sesiones de navegador
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Pasamos el userDetailsService en el constructor como pide la versión nueva
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationMetrics authenticationMetrics(MeterRegistry meterRegistry) {
        return new AuthenticationMetrics(meterRegistry);
    }
}

// Clase interna o externa para manejar las métricas de autenticación
class AuthenticationMetrics {

    private final MeterRegistry meterRegistry;

    public AuthenticationMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        // Inicializa contadores
        meterRegistry.counter("security.auth.success.count");
        meterRegistry.counter("security.auth.failure.count");
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        meterRegistry.counter("security.auth.success.count").increment();
    }

    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
        // Captura fallas de credenciales (la más común)
        meterRegistry.counter("security.auth.failure.count").increment();
    }
}