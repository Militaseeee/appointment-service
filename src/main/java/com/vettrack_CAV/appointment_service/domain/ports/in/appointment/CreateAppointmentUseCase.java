package com.vettrack_CAV.appointment_service.domain.ports.in.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;

// Solicitar cita (PENDIENT -> cHECK Mock -> CONFIRMED/REJECTED)
public interface CreateAppointmentUseCase {
    Appointment create(Appointment appointment);
}
