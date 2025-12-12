package com.vettrack_CAV.appointment_service.domain.ports.in.vet;

import com.vettrack_CAV.appointment_service.domain.model.Vet;

import java.util.List;

public interface GetAllVetUseCase {
    List<Vet> getAll();
}
