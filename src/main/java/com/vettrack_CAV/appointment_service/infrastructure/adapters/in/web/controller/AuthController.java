package com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.controller;

import com.vettrack_CAV.appointment_service.domain.model.user.UserLoginCommand;
import com.vettrack_CAV.appointment_service.domain.model.user.UserRegisterCommand;
import com.vettrack_CAV.appointment_service.domain.ports.in.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterCommand command) {
        authService.register(command);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginCommand command) {
        String token = authService.authenticate(command);
        return ResponseEntity.ok(Map.of("token", token));
    }
}