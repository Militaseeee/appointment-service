package com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.mapper;

import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.dto.request.PetRequest;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.in.web.dto.response.PetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import java.util.List;

// (DTO)
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {

    // Request -> Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Pet toDomain(PetRequest request);

    // Domain -> Response
    PetResponse toResponse(Pet domain);

    List<PetResponse> toResponseList(List<Pet> domainList);
}
