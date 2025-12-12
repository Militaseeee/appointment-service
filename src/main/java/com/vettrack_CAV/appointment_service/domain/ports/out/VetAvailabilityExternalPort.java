package com.vettrack_CAV.appointment_service.domain.ports.out;

import java.time.LocalDate;
import java.time.LocalTime;

// Este es el puerto que nos permitira hablar con el mock service
public interface VetAvailabilityExternalPort {
    boolean isVeterinarianAvailable(Long vetId, LocalDate date, LocalTime time);
}
