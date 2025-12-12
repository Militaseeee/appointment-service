package com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.repository;

import com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {

    // Sobreescribimos findAll para que traiga las relaciones de una vez
    @Override
    @EntityGraph(attributePaths = {"pet", "vet", "diagnosis"})
    List<AppointmentEntity> findAll();

    // Lo mismo para findById
    @Override
    @EntityGraph(attributePaths = {"pet", "vet", "diagnosis"})
    Optional<AppointmentEntity> findById(Long id);
}
