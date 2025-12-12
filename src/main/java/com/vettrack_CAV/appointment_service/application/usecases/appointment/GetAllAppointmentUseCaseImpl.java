package com.vettrack_CAV.appointment_service.application.usecases.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.GetAllAppointmentUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.AppointmentRepositoryPort;

import java.util.List;

public class GetAllAppointmentUseCaseImpl implements GetAllAppointmentUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;

    public GetAllAppointmentUseCaseImpl(AppointmentRepositoryPort appointmentRepositoryPort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
    }

    @Override
    public List<Appointment> gellAll() {
        return appointmentRepositoryPort.findAll();
    }
}
