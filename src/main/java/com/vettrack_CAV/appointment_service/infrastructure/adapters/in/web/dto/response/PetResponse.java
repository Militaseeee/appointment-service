package com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.dto.response;

import com.vettrack_CAV.appointment_service.domain.model.Specie;
import lombok.Data;

@Data
public class PetResponse {
    private Long id;
    private String name;
    private Specie specie;
    private String breed;
    private Integer age;
    private String ownerName;
    private Boolean active;
}
