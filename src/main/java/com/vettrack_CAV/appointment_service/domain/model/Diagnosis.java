package com.vettrack_CAV.appointment_service.domain.model;

public class Diagnosis {
    private Long id;
    private String description;
    private String suggestedTreatment; // tratamiento sugerido
    private String recommendations;

    public Diagnosis() {
    }

    public Diagnosis(Long id, String description, String suggestedTreatment, String recommendations) {
        this.id = id;
        this.description = description;
        this.suggestedTreatment = suggestedTreatment;
        this.recommendations = recommendations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuggestedTreatment() {
        return suggestedTreatment;
    }

    public void setSuggestedTreatment(String suggestedTreatment) {
        this.suggestedTreatment = suggestedTreatment;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}
