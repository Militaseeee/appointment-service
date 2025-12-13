package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.mapper;

import com.vettrack_CAV.appointment_service.domain.model.Appointment;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {PetJpaMapper.class, VetMapper.class, DiagnosisMapper.class})
public interface AppointmentJpaMapper {
    Appointment toDomain(AppointmentEntity entity);
    AppointmentEntity toEntity(Appointment domain);
    List<Appointment> toDomainList(List<AppointmentEntity> entities);
}
