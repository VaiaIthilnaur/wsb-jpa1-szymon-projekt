package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.MedicalTreatmentTO;
import com.capgemini.wsb.mapper.MedicalTreatmentMapper;
import com.capgemini.wsb.persistence.dao.MedicalTreatmentDao;
import com.capgemini.wsb.persistence.entity.MedicalTreatmentEntity;
import com.capgemini.wsb.service.MedicalTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicalTreatmentServiceImpl implements MedicalTreatmentService {
    private final MedicalTreatmentDao treatmentDao;

    @Autowired
    public MedicalTreatmentServiceImpl(MedicalTreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }

    @Override
    public MedicalTreatmentTO findById(Long id) {
        MedicalTreatmentEntity entity = treatmentDao.findOne(id);
        return MedicalTreatmentMapper.mapToTO(entity);
    }

    @Override
    public MedicalTreatmentTO save(MedicalTreatmentTO treatment) {
        MedicalTreatmentEntity entity = MedicalTreatmentMapper.mapToEntity(treatment);
        MedicalTreatmentEntity savedEntity = treatmentDao.save(entity);
        return MedicalTreatmentMapper.mapToTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        treatmentDao.delete(id);
    }
}