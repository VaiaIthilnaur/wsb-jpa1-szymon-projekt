package com.capgemini.wsb.dto;

import com.capgemini.wsb.persistence.enums.TreatmentType;
import java.io.Serializable;

public class MedicalTreatmentTO implements Serializable {
    private Long id;
    private String description;
    private TreatmentType type;

    // Gettery i settery

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

    public TreatmentType getType() {
        return type;
    }

    public void setType(TreatmentType type) {
        this.type = type;
    }
}