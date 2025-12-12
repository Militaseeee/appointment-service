package com.vettrack_CAV.appointment_service.domain.ports.out;

import com.vettrack_CAV.appointment_service.domain.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepositoryPort {
    Pet save(Pet pet);
    Optional<Pet> findById(Long id);
    List<Pet> findAllByOwnerId(String ownerId);
}
