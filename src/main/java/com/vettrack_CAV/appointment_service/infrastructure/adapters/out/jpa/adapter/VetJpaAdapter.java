package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.adapter;

import com.vettrack_CAV.appointment_service.domain.model.Vet;
import com.vettrack_CAV.appointment_service.domain.ports.out.VetRepositoryPort;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.mapper.VetMapper;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.repository.VetJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VetJpaAdapter implements VetRepositoryPort {

    private final VetJpaRepository vetJpaRepository;
    private final VetMapper vetMapper;

    @Override
    public Optional<Vet> findById(Long id) {
        return vetJpaRepository.findById(id)
                .map(vetMapper::toDomain);
    }

    @Override
    public List<Vet> findAll() {
        return vetMapper.toDomainList(vetJpaRepository.findAll());
    }
}
