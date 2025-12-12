package com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.controller;

import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.domain.ports.in.pet.CreatePetUseCase;
import com.vettrack_CAV.appointment_service.domain.ports.in.pet.GetPetUseCase;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.dto.request.PetRequest;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.dto.response.PetResponse;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.mapper.PetMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final CreatePetUseCase createPetUseCase;
    private final GetPetUseCase getPetUseCase;
    private final PetMapper petMapper; // El de los DTO


    @PostMapping
    public ResponseEntity<PetResponse> registrar(@RequestBody @Valid PetRequest request) {
        // Request -> Domain
        Pet petDomain = petMapper.toDomain(request);

        // UseCase
        Pet create = createPetUseCase.create(petDomain);

        // Domain -> Response
        return new ResponseEntity<>(petMapper.toResponse(create), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> getByOwner(@RequestParam String ownerId) {
        List<Pet> pet = getPetUseCase.getByOwner(ownerId);
        return ResponseEntity.ok(petMapper.toResponseList(pet));
    }
}
