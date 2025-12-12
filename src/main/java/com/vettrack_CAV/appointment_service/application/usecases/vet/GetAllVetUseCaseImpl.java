package com.vettrack_CAV.appointment_service.application.usecases.vet;

import com.vettrack_CAV.appointment_service.domain.model.Vet;
import com.vettrack_CAV.appointment_service.domain.ports.in.vet.GetAllVetUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.out.VetRepositoryPort;

import java.util.List;

public class GetAllVetUseCaseImpl implements GetAllVetUseCase {

    private final VetRepositoryPort vetRepositoryPort;

    public GetAllVetUseCaseImpl(VetRepositoryPort vetRepositoryPort) {
        this.vetRepositoryPort = vetRepositoryPort;
    }

    @Override
    public List<Vet> getAll() {
        return vetRepositoryPort.findAll();
    }
}
