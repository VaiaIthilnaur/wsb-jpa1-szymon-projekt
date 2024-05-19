package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.DoctorTO;

public interface DoctorService {
    DoctorTO findById(Long id);
    DoctorTO save(DoctorTO doctor);
    void delete(Long id);
}