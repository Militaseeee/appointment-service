package com.vettrack_CAV.appointment_service.domain.ports.in.user;


import com.vettrack_CAV.appointment_service.domain.model.user.UserLoginCommand;
import com.vettrack_CAV.appointment_service.domain.model.user.UserRegisterCommand;

public interface AuthService {

    void register(UserRegisterCommand command);

    String authenticate(UserLoginCommand command);
}