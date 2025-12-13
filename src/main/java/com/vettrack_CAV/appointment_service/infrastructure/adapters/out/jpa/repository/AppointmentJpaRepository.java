package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.repository;

import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT a FROM AppointmentEntity a WHERE a.pet.ownerId = :cedula")
    List<AppointmentEntity> findByPetOwnerId(@Param("cedula") String cedula);

    @Query("SELECT a FROM AppointmentEntity a JOIN a.vet v WHERE v.email = :email")
    List<AppointmentEntity> findByVetUsername(@Param("email") String email);
}
