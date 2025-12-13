package com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.mapper;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.dto.request.AppointmentRequest;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.dto.response.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {PetWebMapper.class})
public interface AppointmentWebMapper {

    // Request -> Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reason", ignore = true) // estado <_--- HAY QUE VER
    @Mapping(target = "diagnosis", ignore = true)
    @Mapping(source = "petId", target = "pet.id")
    @Mapping(source = "vetId", target = "vet.id")
    Appointment toDomain(AppointmentRequest request);

    // Domain -> Response
    @Mapping(source = "vet.name", target = "vetName")
    AppointmentResponse toResponse(Appointment domain);

    List<AppointmentResponse> toResponseList(List<Appointment> domainList);
}
