package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor/{id}")
    DoctorTO findById(@PathVariable Long id) {
        DoctorTO doctor = doctorService.findById(id);
        if (doctor != null) {
            return doctor;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping("/doctor")
    DoctorTO save(@RequestBody DoctorTO doctor) {
        return doctorService.save(doctor);
    }

    @DeleteMapping("/doctor/{id}")
    void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}