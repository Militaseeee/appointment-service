package com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.controller;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.domain.model.Diagnosis;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.CreateAppointmentUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.GetAllAppointmentUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.in.appointment.RegisterDiagnosisUseCase;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.dto.request.AppointmentRequest;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.dto.response.AppointmentResponse;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.mapper.AppointmentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final GetAllAppointmentUseCase getAllAppointmentUseCase;
    private final RegisterDiagnosisUseCase registerDiagnosisUseCase;

    private final AppointmentMapper appointmentMapper;

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody @Valid AppointmentRequest request) {
        // Request -> Domain
        Appointment appointmentDomain = appointmentMapper.toDomain(request);

        // UseCase
        Appointment create = createAppointmentUseCase.create(appointmentDomain);

        // Domain -> Response
        return new ResponseEntity<>(appointmentMapper.toResponse(create), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAll() {
        List<Appointment> appointment = getAllAppointmentUseCase.gellAll();
        return ResponseEntity.ok(appointmentMapper.toResponseList(appointment));
    }

    // Endpoint extra para diagnóstico (simplificado)
    @PostMapping("/{id}/diagnostico")
    public ResponseEntity<Void> registerDiagnosis(@PathVariable Long id, @RequestBody Diagnosis diagnosis) {
        registerDiagnosisUseCase.registerDiagnosis(id, diagnosis);
        return ResponseEntity.noContent().build();
    }
}
