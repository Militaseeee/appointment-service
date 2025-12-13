package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.adapter;

import com.vettrack_CAV.appointment_service.domain.model.UserModel;
import com.vettrack_CAV.appointment_service.domain.ports.out.UserRepositoryPort;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity.UserEntity;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserModel save(UserModel userModel) {
        UserEntity entity = new UserEntity(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getRole()
        );
        UserEntity saved = userJpaRepository.save(entity);
        return new UserModel(saved.getId(), saved.getUsername(), saved.getPassword(), saved.getRole());
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(entity -> new UserModel(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getRole()));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }
}