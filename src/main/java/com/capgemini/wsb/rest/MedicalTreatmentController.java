package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.MedicalTreatmentTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.MedicalTreatmentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalTreatmentController {

    private final MedicalTreatmentService treatmentService;

    public MedicalTreatmentController(MedicalTreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping("/treatment/{id}")
    MedicalTreatmentTO findById(@PathVariable Long id) {
        MedicalTreatmentTO treatment = treatmentService.findById(id);
        if (treatment != null) {
            return treatment;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping("/treatment")
    MedicalTreatmentTO save(@RequestBody MedicalTreatmentTO treatment) {
        return treatmentService.save(treatment);
    }
    @DeleteMapping("/treatment/{id}")
    void delete(@PathVariable Long id) {
        treatmentService.delete(id);
    }
    }