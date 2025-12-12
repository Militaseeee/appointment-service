package com.vettrack_CAV.appointment_service.application.usecases.appointment;

import com.vettrack_CAV.appointment_service.domain.exception.BusinessException;
import com.vettrack_CAV.appointment_service.domain.exception.ResourceNotFoundException;
import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.model.Diagnosis;
import com.vettrack_CAV.appointment_service.domain.model.StateAppointment;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.RegisterDiagnosisUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.AppointmentRepositoryPort;

public class RegisterDiagnosisUseCaseImpl implements RegisterDiagnosisUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;

    public RegisterDiagnosisUseCaseImpl(AppointmentRepositoryPort appointmentRepositoryPort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
    }

    @Override
    public void registerDiagnosis(Long appointmentId, Diagnosis diagnosis) {
        Appointment appointment = appointmentRepositoryPort.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment with ID: " + appointmentId + " not found"));

        if (appointment.getStateAppointment() != StateAppointment.CONFIRMED) {
            throw new BusinessException("Only CONFIRMED appointments can be diagnosed");
        }

        appointment.setDiagnosis(diagnosis);
        appointmentRepositoryPort.save(appointment);
    }
}
