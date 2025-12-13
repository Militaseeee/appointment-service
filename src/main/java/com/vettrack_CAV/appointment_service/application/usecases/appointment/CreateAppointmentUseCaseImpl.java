package com.vettrack_CAV.appointment_service.application.usecases.appointment;

import com.vettrack_CAV.appointment_service.domain.exception.BusinessException;
import com.vettrack_CAV.appointment_service.domain.exception.ResourceNotFoundException;
import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.domain.model.StateAppointment;
import com.vettrack_CAV.appointment_service.domain.model.Vet;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.CreateAppointmentUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.AppointmentRepositoryPort;
import com.vettrack_CAV.appointment_service.domain.ports.out.PetRepositoryPort;
import com.vettrack_CAV.appointment_service.domain.ports.out.VetAvailabilityExternalPort;
import com.vettrack_CAV.appointment_service.domain.ports.out.VetRepositoryPort;

public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;
    private final PetRepositoryPort petRepositoryPort;
    private final VetRepositoryPort vetRepositoryPort;
    private final VetAvailabilityExternalPort vetAvailabilityExternalPort;

    public CreateAppointmentUseCaseImpl(AppointmentRepositoryPort appointmentRepositoryPort, PetRepositoryPort petRepositoryPort, VetRepositoryPort vetRepositoryPort, VetAvailabilityExternalPort vetAvailabilityExternalPort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
        this.petRepositoryPort = petRepositoryPort;
        this.vetRepositoryPort = vetRepositoryPort;
        this.vetAvailabilityExternalPort = vetAvailabilityExternalPort;
    }

    @Override
    public Appointment create(Appointment appointment) {
            Pet pet = petRepositoryPort.findById(appointment.getPet().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet with ID " + appointment.getPet().getId() + " not found"));

        if (!Boolean.TRUE.equals(pet.getActive())) {
            throw new BusinessException("The pet is not active");
        }

        appointment.setPet(pet);

        Vet vet = vetRepositoryPort.findById(appointment.getVet().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vet with ID " + appointment.getVet().getId() + " not found"));

        appointment.setVet(vet);

        appointment.setStateAppointment(StateAppointment.PENDING);

        boolean available = vetAvailabilityExternalPort.isVeterinarianAvailable(
                appointment.getVet().getId(),
                appointment.getDate(),
                appointment.getTime()
        );

        if (available) {
            appointment.setStateAppointment(StateAppointment.CONFIRMED);
        } else {
            appointment.setStateAppointment(StateAppointment.REJECTED);
        }

        return appointmentRepositoryPort.save(appointment);
    }
}
