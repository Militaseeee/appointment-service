package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.repository;

import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity.VetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetJpaRepository extends JpaRepository<VetEntity, Long> {
}
