package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.mapper;

import com.vettrack_CAV.appointment_service.domain.model.Vet;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity.VetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VetMapper {
    Vet toDomain(VetEntity entity);
    VetEntity toEntity(Vet domain);
    List<Vet> toDomainList(List<VetEntity> entities);
}
