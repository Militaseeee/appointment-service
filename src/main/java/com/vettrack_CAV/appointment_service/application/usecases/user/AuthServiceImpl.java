package com.vettrack_CAV.appointment_service.application.usecases.user;

import com.vettrack_CAV.appointment_service.domain.exception.BusinessException; // Asumo que tienes una excepción genérica
import com.vettrack_CAV.appointment_service.domain.model.UserModel;
import com.vettrack_CAV.appointment_service.domain.model.user.UserLoginCommand;
import com.vettrack_CAV.appointment_service.domain.model.user.UserRegisterCommand;
import com.vettrack_CAV.appointment_service.domain.ports.in.user.AuthService;
import com.vettrack_CAV.appointment_service.domain.ports.out.UserRepositoryPort;
import com.vettrack_CAV.appointment_service.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(UserRegisterCommand command) {
        if (userRepositoryPort.existsByUsername(command.username())) {
            throw new RuntimeException("El usuario ya existe");
        }

        UserModel user = new UserModel();
        user.setUsername(command.username());
        user.setPassword(passwordEncoder.encode(command.password()));
        user.setRole(command.role());

        user.setIdentification(command.identification());

        userRepositoryPort.save(user);
    }

    @Override
    public String authenticate(UserLoginCommand command) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.username(),
                        command.password()
                )
        );

        // Si la autenticación falla, lanza excepción automáticamente. Si pasa:
        UserModel user = userRepositoryPort.findByUsername(command.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return jwtService.generateToken(user);
    }
}