package com.vettrack_CAV.appointment_service.application.usecases.pet;

import com.vettrack_CAV.appointment_service.domain.exception.BusinessException;
import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.domain.ports.in.pet.CreatePetUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.PetRepositoryPort;

public class CreatePetUseCaseImpl implements CreatePetUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public CreatePetUseCaseImpl(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public Pet create(Pet pet) {
        if (pet.getAge() == null || pet.getAge() <= 0) {
            throw new BusinessException("The pet's age must be greater than 0");
        }
        if (pet.getActive() == null) {
            pet.setActive(true);
        }
        return petRepositoryPort.save(pet);
    }
}
