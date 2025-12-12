package com.vettrack_CAV.appointment_service.domain.ports.in.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;

import java.util.List;

// soLO PARA EL ADMIN
public interface GetAllAppointmentUseCase {
    List<Appointment> gellAll();
}
