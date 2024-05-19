package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.MedicalTreatmentTO;

public interface MedicalTreatmentService {
    MedicalTreatmentTO findById(Long id);
    MedicalTreatmentTO save(MedicalTreatmentTO treatment);
    void delete(Long id);
}