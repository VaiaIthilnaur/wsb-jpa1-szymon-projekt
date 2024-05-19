package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.AddressTO;
import com.capgemini.wsb.mapper.AddressMapper;
import com.capgemini.wsb.persistence.dao.AddressDao;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public AddressTO findById(Long id) {
        AddressEntity entity = addressDao.findOne(id);
        return AddressMapper.mapToTO(entity);
    }

    @Override
    public AddressTO save(AddressTO address) {
        AddressEntity entity = AddressMapper.mapToEntity(address);
        AddressEntity savedEntity = addressDao.save(entity);
        return AddressMapper.mapToTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        addressDao.delete(id);
    }
}