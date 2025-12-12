package com.vettrack_CAV.appointment_service.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Long id;
    private Pet pet;
    private Vet vet;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private StateAppointment stateAppointment;
    private Diagnosis diagnosis;

    public Appointment() {
    }

    public Appointment(Long id, Pet pet, Vet vet, LocalDate date, LocalTime time, String reason, StateAppointment stateAppointment, Diagnosis diagnosis) {
        this.id = id;
        this.pet = pet;
        this.vet = vet;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.stateAppointment = stateAppointment;
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public StateAppointment getStateAppointment() {
        return stateAppointment;
    }

    public void setStateAppointment(StateAppointment stateAppointment) {
        this.stateAppointment = stateAppointment;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
}
