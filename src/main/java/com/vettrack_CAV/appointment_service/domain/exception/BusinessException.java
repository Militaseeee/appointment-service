package com.vettrack_CAV.appointment_service.domain.exception;

// 400 Bad Request / 409 Conflict
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
