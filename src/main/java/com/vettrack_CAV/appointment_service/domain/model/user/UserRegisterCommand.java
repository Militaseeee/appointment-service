package com.vettrack_CAV.appointment_service.domain.model.user;

import com.vettrack_CAV.appointment_service.domain.model.Role;

public record UserRegisterCommand(
        String username,
        String password,
        Role role
) {}