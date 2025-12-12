package com.vettrack_CAV.appointment_service.infraestructure.adapters.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diagnosis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String suggestedTreatment; // tratamiento sugerido
    private String recommendations;
}