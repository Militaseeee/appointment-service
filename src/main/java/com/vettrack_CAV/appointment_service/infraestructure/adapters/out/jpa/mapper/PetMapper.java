package com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.mapper;

import com.vettrack_CAV.appointment_service.domain.model.Pet;
import com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.entity.PetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {
    // Convierte lo que sale de la BD (Entity) a lo que entiende el negocio (Domain)
    Pet toDomain(PetEntity entity);
    // Convierte lo que manda el negocio (Domain) a lo que guarda la BD (Entity)
    PetEntity toEntity(Pet domain);
    List<Pet> toDomainList(List<PetEntity> entities);
}
