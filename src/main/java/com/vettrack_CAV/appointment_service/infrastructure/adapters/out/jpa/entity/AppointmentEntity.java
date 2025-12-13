package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.jpa.entity;

import com.vettrack_CAV.appointment_service.domain.model.StateAppointment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lazy para rendimiento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vet_id")
    private VetEntity vet;

    private LocalDate date;
    private LocalTime time;
    private String reason;

    @Enumerated(EnumType.STRING)
    private StateAppointment stateAppointment;

    // Relacion 1 a 1 con Diagnostico (Cascade ALL para guardar diagnostico al guardar la cita)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private DiagnosisEntity diagnosis;

}
