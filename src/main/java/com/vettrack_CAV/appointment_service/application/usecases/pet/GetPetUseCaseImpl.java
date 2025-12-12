package com.vettrack_CAV.appointment_service.application.usecases.pet;

import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.domain.ports.in.pet.GetPetUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.PetRepositoryPort;

import java.util.List;

public class GetPetUseCaseImpl implements GetPetUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public GetPetUseCaseImpl(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public List<Pet> getByOwner(String ownerId) {
        return petRepositoryPort.findAllByOwnerId(ownerId);
    }
}
