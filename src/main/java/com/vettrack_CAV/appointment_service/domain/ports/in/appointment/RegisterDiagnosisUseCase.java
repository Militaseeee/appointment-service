package com.vettrack_CAV.appointment_service.domain.ports.in.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Diagnosis;

// Registrar diagnosticos -> Cuando el veterinario atiende la cita
public interface RegisterDiagnosisUseCase {
    void registerDiagnosis(Long appointmentId, Diagnosis diagnosis);
}
