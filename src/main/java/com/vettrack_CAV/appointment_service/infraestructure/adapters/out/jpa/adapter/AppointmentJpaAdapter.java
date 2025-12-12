package com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.adapter;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.ports.out.AppointmentRepositoryPort;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.entity.AppointmentEntity;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.mapper.AppointmentMapper;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.repository.AppointmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppointmentJpaAdapter implements AppointmentRepositoryPort {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity entity = appointmentMapper.toEntity(appointment);
        AppointmentEntity saved = appointmentJpaRepository.save(entity);
        return appointmentMapper.toDomain(saved);
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentJpaRepository.findById(id)
                .map(appointmentMapper::toDomain);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentMapper.toDomainList(appointmentJpaRepository.findAll());
    }
}
