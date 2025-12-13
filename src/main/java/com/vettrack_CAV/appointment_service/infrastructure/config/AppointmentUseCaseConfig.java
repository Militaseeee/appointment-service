package com.vettrack_CAV.appointment_service.infrastructure.config;

import com.vettrack_CAV.appointment_service.application.usecases.appointment.*;
import com.vettrack_CAV.appointment_service.application.usecases.pet.*;
import com.vettrack_CAV.appointment_service.application.usecases.vet.*;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.*;
import com.vettrack_CAV.appointment_service.domain.ports.in.pet.*;
import com.vettrack_CAV.appointment_service.domain.ports.in.vet.*;
import com.vettrack_CAV.appointment_service.domain.ports.out.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppointmentUseCaseConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // --- CASOS DE USO DE APPOINTMENT (CITA) ---

    @Bean
    public CreateAppointmentUseCase createAppointmentUseCase(
            AppointmentRepositoryPort appointmentRepositoryPort,
            PetRepositoryPort petRepositoryPort,
            VetRepositoryPort vetRepositoryPort,
            VetAvailabilityExternalPort vetAvailabilityExternalPort) {
        return new CreateAppointmentUseCaseImpl(
                appointmentRepositoryPort,
                petRepositoryPort,
                vetRepositoryPort,
                vetAvailabilityExternalPort
        );
    }

    @Bean
    public GetAllAppointmentUseCase getAllAppointmentUseCase(
            AppointmentRepositoryPort appointmentRepositoryPort) {
        return new GetAllAppointmentUseCaseImpl(appointmentRepositoryPort);
    }

    @Bean
    public GetAppointmentUseCase getAppointmentUseCase(
            AppointmentRepositoryPort appointmentRepositoryPort) {
        return new GetAppointmentUseCaseImpl(appointmentRepositoryPort);
    }

    @Bean
    public RegisterDiagnosisUseCase registerDiagnosisUseCase(
            AppointmentRepositoryPort appointmentRepositoryPort) {
        return new RegisterDiagnosisUseCaseImpl(appointmentRepositoryPort);
    }

    // --- CASOS DE USO DE PET (MASCOTA) ---

    @Bean
    public CreatePetUseCase createPetUseCase(PetRepositoryPort petRepositoryPort) {
        return new CreatePetUseCaseImpl(petRepositoryPort);
    }

    @Bean
    public GetPetUseCase getPetUseCase(PetRepositoryPort petRepositoryPort) {
        return new GetPetUseCaseImpl(petRepositoryPort);
    }

    // --- CASOS DE USO DE VET (VETERINARIO) ---

    @Bean
    public GetAllVetUseCase getAllVetUseCase(VetRepositoryPort vetRepositoryPort) {
        return new GetAllVetUseCaseImpl(vetRepositoryPort);
    }
}