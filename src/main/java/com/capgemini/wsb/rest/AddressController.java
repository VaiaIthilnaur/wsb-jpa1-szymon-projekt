package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.AddressTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.AddressService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/{id}")
    AddressTO findById(@PathVariable Long id) {
        AddressTO address = addressService.findById(id);
        if (address != null) {
            return address;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping("/address")
    AddressTO save(@RequestBody AddressTO address) {
        return addressService.save(address);
    }

    @DeleteMapping("/address/{id}")
    void delete(@PathVariable Long id) {
        addressService.delete(id);
    }
}