package com.vettrack_CAV.appointment_service.domain.ports.in.pet;

import com.vettrack_CAV.appointment_service.domain.model.Pet;

public interface CreatePetUseCase {
    Pet create(Pet pet);
}
