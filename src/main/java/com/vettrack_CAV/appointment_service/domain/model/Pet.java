package com.vettrack_CAV.appointment_service.domain.model;

public class Pet {
    private Long id;
    private String name;
    private Specie specie;
    private String breed; // Raza
    private Integer age;
    private String ownerName;
    private String ownerId;
    private Boolean active;

    public Pet() {
    }

    public Pet(Long id, String name, Specie specie, String breed, Integer age, String ownerName, String ownerId, Boolean active) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.age = age;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.active = active;
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

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
