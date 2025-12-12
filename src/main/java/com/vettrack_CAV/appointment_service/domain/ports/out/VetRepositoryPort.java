package com.vettrack_CAV.appointment_service.domain.ports.out;

import com.vettrack_CAV.appointment_service.domain.model.Vet;

import java.util.List;
import java.util.Optional;

public interface VetRepositoryPort {
    Optional<Vet> findById(Long id); // Verificar si existe
    List<Vet> findAll();
}
