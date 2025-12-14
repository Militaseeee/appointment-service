package com.vettrack_CAV.appointment_service.application.usecases.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.domain.model.Vet;
import com.vettrack_CAV.appointment_service.domain.ports.out.AppointmentRepositoryPort;
import com.vettrack_CAV.appointment_service.domain.ports.out.PetRepositoryPort;
import com.vettrack_CAV.appointment_service.domain.ports.out.VetAvailabilityExternalPort;
import com.vettrack_CAV.appointment_service.domain.ports.out.VetRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAppointmentUseCaseTest {

    @Mock private AppointmentRepositoryPort appointmentRepositoryPort;
    @Mock private PetRepositoryPort petRepositoryPort;
    @Mock private VetRepositoryPort vetRepositoryPort;
    @Mock private VetAvailabilityExternalPort vetAvailabilityExternalPort;

    @InjectMocks
    private CreateAppointmentUseCaseImpl createAppointmentUseCase;

    @Test
    void shouldCreateAppointment_WhenDataIsValid() {
        // GIVEN (Preparar datos)
        Long petId = 1L;
        Long vetId = 2L;

        // 1. Crear objetos Pet y Vet SOLO con ID para el INPUT
        Pet inputPet = new Pet();
        inputPet.setId(petId);

        Vet inputVet = new Vet();
        inputVet.setId(vetId);

        Appointment newAppt = new Appointment();
        newAppt.setPet(inputPet); // <--- ¡ESTA ES LA LÍNEA CLAVE QUE FALTA!
        newAppt.setVet(inputVet); // <-- ¡SOLUCIÓN 2: Establecer Vet!
        newAppt.setDate(LocalDate.now().plusDays(1));
        newAppt.setTime(LocalTime.of(10, 0));

        // 2. Simular las dependencias (lo que el UseCase encontrará en el repo)
        Pet mockPet = new Pet();
        mockPet.setId(petId);
        mockPet.setActive(true);

        Vet mockVet = new Vet();
        mockVet.setId(vetId);

        // 3. Stubbing de repositorios (Lo que deben devolver)
        when(petRepositoryPort.findById(petId)).thenReturn(Optional.of(mockPet));
        when(vetRepositoryPort.findById(vetId)).thenReturn(Optional.of(mockVet));

        // 4. Stubbing del servicio externo (Solución al PotentialStubbingProblem anterior)
        when(vetAvailabilityExternalPort.isVeterinarianAvailable(
                any(Long.class),
                any(LocalDate.class),
                any(LocalTime.class)
        )).thenReturn(true);

        when(appointmentRepositoryPort.save(any(Appointment.class))).thenReturn(newAppt);

        // WHEN (Ejecutar)
        Appointment result = createAppointmentUseCase.create(newAppt);

        // THEN (Verificar)
        assertNotNull(result);
        verify(appointmentRepositoryPort).save(any(Appointment.class));
    }
}