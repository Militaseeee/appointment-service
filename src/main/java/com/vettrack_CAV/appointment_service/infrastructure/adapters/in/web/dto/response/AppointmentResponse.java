package com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.dto.response;

import com.vettrack_CAV.appointment_service.domain.model.StateAppointment;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentResponse {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private StateAppointment stateAppointment;

    // Podemos devolver objetos anidados simples o solo nombres
    private PetResponse pet;
    private String VetName;
}
