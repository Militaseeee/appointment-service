package com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.exception;

import com.vettrack_CAV.appointment_service.domain.exception.BusinessException;
import com.vettrack_CAV.appointment_service.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;

// Manejo Global de Errores (Requirement 5)
//El requerimiento pide explícitamente ProblemDetail (RFC 7807)

@RestControllerAdvice
public class GlobalControllerAdvice {

    // (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("https://vettrack.com/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // 2. Manejo de Reglas de Negocio (400)
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Business Rule Error");
        problemDetail.setType(URI.create("https://vettrack.com/errors/business-rule"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // 3. Manejo de Validaciones de DTO (@NotNull, @NotBlank)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Concatenamos todos los errores (ej: "nombre: obligatorio, edad: debe ser mayor a 0")
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorMessage);
        problemDetail.setTitle("Validation Error");
        problemDetail.setType(URI.create("https://vettrack.com/errors/validation"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
