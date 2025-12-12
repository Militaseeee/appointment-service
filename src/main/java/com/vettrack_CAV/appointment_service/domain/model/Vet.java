package com.vettrack_CAV.appointment_service.domain.model;

public class Vet {
    private Long id;
    private String name;
    private String specialty; // especialidad
    private String email;

    public Vet() {
    }

    public Vet(Long id, String name, String specialty, String email) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
