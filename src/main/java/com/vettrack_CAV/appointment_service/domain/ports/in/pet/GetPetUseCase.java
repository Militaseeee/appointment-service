package com.vettrack_CAV.appointment_service.domain.ports.in.pet;

import com.vettrack_CAV.appointment_service.domain.model.Pet;

import java.util.List;

public interface GetPetUseCase {
    List<Pet> getByOwner(String ownerId);
}
