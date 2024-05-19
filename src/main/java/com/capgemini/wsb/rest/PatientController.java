package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/{id}")
    PatientTO findById(@PathVariable Long id) {
        PatientTO patient = patientService.findById(id);
        if (patient != null) {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping("/patient")
    PatientTO save(@RequestBody PatientTO patient) {
        return patientService.save(patient);
    }

    @DeleteMapping("/patient/{id}")
    void delete(@PathVariable Long id) {
        patientService.delete(id);
    }
}