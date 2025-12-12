package com.vettrack_CAV.appointment_service.domain.ports.in.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;

import java.util.Optional;

public interface GetAppointmentUseCase {
    Optional<Appointment> getById(Long id);
}
