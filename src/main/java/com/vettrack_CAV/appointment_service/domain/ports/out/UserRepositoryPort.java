package com.vettrack_CAV.appointment_service.domain.ports.out;

import com.vettrack_CAV.appointment_service.domain.model.UserModel;

import java.util.Optional;

public interface UserRepositoryPort {
    UserModel save(UserModel userModel);
    Optional<UserModel> findByUsername(String username);

    boolean existsByUsername(String username);
}