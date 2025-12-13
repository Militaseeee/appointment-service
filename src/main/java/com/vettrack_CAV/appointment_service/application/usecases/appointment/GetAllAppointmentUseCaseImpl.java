package com.vettrack_CAV.appointment_service.application.usecases.appointment;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.model.Role;
import com.vettrack_CAV.appointment_service.domain.model.UserModel;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.GetAllAppointmentUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.AppointmentRepositoryPort;
import com.vettrack_CAV.appointment_service.domain.ports.out.UserRepositoryPort;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class GetAllAppointmentUseCaseImpl implements GetAllAppointmentUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public GetAllAppointmentUseCaseImpl(AppointmentRepositoryPort appointmentRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<Appointment> gellAll() {
        // 1. Obtener usuario logueado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        // 2. Buscar sus datos (incluyendo la cédula)
        UserModel currentUser = userRepositoryPort.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Lógica de roles
        if (currentUser.getRole() == Role.ROLE_ADMIN) {
            return appointmentRepositoryPort.findAll();
        }

        if (currentUser.getRole() == Role.ROLE_VET) {
            return appointmentRepositoryPort.findAllByVet(currentUser.getUsername());
        }

        if (currentUser.getRole() == Role.ROLE_OWNER) {
            // ¡MAGIA! Aquí usamos la cédula que guardamos en el registro
            // para buscar las mascotas que tengan ese ownerId
            String cedula = currentUser.getIdentification();
            return appointmentRepositoryPort.findAllByPetOwner(cedula);
        }

        return List.of();
    }
}
