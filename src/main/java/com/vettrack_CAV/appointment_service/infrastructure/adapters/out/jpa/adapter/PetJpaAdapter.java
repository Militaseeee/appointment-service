package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.adapter;

import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.domain.ports.out.PetRepositoryPort;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity.PetEntity;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.mapper.PetJpaMapper;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.repository.PetJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PetJpaAdapter implements PetRepositoryPort {

    private final PetJpaRepository petJpaRepository;
    private final PetJpaMapper petMapper;

    @Override
    public Pet save(Pet pet) {
        PetEntity entity = petMapper.toEntity(pet);
        PetEntity saved = petJpaRepository.save(entity);
        return petMapper.toDomain(saved);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return petJpaRepository.findById(id)
                .map(petMapper::toDomain);
    }

    @Override
    public List<Pet> findAllByOwnerId(String ownerId) {
        List<PetEntity> entities = petJpaRepository.findAllByOwnerId(ownerId);
        return petMapper.toDomainList(entities);
    }
}
