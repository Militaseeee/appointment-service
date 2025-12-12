package com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.mapper;

import com.vettrack_CAV.appointment_service.domain.model.Diagnosis;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.entity.DiagnosisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DiagnosisMapper {
    Diagnosis toDomain(DiagnosisEntity entity);
    DiagnosisEntity toEntity(Diagnosis domain);
}
