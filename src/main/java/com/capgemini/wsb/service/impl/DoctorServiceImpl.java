package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.mapper.DoctorMapper;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public DoctorTO findById(Long id) {
        DoctorEntity entity = doctorDao.findOne(id);
        return DoctorMapper.mapToTO(entity);
    }

    @Override
    public DoctorTO save(DoctorTO doctor) {
        DoctorEntity entity = DoctorMapper.mapToEntity(doctor);
        DoctorEntity savedEntity = doctorDao.save(entity);
        return DoctorMapper.mapToTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        doctorDao.delete(id);
    }
}