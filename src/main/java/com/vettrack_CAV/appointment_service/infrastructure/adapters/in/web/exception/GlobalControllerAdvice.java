package com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.exception;

import com.vettrack_CAV.appointment_service.domain.exception.BusinessException;
import com.vettrack_CAV.appointment_service.domain.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest; // <--- Necesario para 'instance'
import lombok.extern.slf4j.Slf4j; // <--- Necesario para Logging
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException; // <--- Seguridad
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j // 1. loggin estrucutrado
public class GlobalControllerAdvice {

    // (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        log.error("Resource not found exception: {}", ex.getMessage()); // Log

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("https://vettrack.com/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setInstance(URI.create(request.getRequestURI())); // 2. INSTANCE (Requerimiento)
        // traceId suele venir de herramientas como Micrometer/Sleuth, si no tienes, puedes generar uno o no ponerlo.
        return problemDetail;
    }

    // (400) Reglas de Negocio
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, HttpServletRequest request) {
        log.warn("Business rule violation: {}", ex.getMessage()); // Log warn es mejor para negocio

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Business Rule Error");
        problemDetail.setType(URI.create("https://vettrack.com/errors/business-rule"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    // (400) Validaciones (@NotNull, @Future)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.warn("Validation failed: {}", errorMessage); // Log

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorMessage);
        problemDetail.setTitle("Validation Error");
        problemDetail.setType(URI.create("https://vettrack.com/errors/validation"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    // (403) ACCESO DENEGADO (Faltaba esto para seguridad)
    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.error("Access denied for user: {}", request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "Anonymous");

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "No tienes permisos para realizar esta acción.");
        problemDetail.setTitle("Access Denied");
        problemDetail.setType(URI.create("https://vettrack.com/errors/forbidden"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    // (500) Error inesperado (Siempre es bueno tenerlo)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGlobalException(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error occurred", ex); // Log con stacktrace completo

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error interno inesperado.");
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(URI.create("https://vettrack.com/errors/internal-server-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }
}