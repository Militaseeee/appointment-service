package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.repository;

import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}