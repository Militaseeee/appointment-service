package com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.entity;

import com.vettrack_CAV.appointment_service.domain.model.Specie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Specie specie;
    private String breed; // Raza
    private Integer age;
    private String ownerName;
    private String ownerId;
    private Boolean active;

}