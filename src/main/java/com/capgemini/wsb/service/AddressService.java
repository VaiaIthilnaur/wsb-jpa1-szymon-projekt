package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.AddressTO;

public interface AddressService {
    AddressTO findById(Long id);
    AddressTO save(AddressTO address);
    void delete(Long id);
}