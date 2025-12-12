package com.vettrack_CAV.appointment_service.application.usecases.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.GetAppointmentUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.AppointmentRepositoryPort;

import java.util.Optional;

public class GetAppointmentUseCaseImpl implements GetAppointmentUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;

    public GetAppointmentUseCaseImpl(AppointmentRepositoryPort appointmentRepositoryPort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
    }

    @Override
    public Optional<Appointment> getById(Long id) {
        return appointmentRepositoryPort.findById(id);
    }
}
