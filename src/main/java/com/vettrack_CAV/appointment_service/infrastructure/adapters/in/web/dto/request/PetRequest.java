package com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.dto.request;

import com.vettrack_CAV.appointment_service.domain.model.Specie;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "The species is mandatory")
    private Specie specie;

    @NotBlank
    private String breed;

    @Min(value = 1, message = "The age must be greater than 0")
    private Integer age;

    @NotBlank
    private String ownerName;

    @NotBlank
    private String ownerId;
}