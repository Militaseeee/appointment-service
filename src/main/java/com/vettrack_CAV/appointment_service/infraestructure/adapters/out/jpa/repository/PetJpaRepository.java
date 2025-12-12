package com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.repository;

import com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetJpaRepository extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findAllByOwnerId(String ownerId);
}
