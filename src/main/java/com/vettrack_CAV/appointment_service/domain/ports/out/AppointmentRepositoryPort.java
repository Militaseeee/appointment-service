package com.vettrack_CAV.appointment_service.domain.ports.out;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepositoryPort {
    Appointment save(Appointment appointment);
    Optional<Appointment> findById(Long id);
    List<Appointment> findAll();
}
