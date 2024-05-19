package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.MedicalTreatmentDao;
import com.capgemini.wsb.persistence.entity.MedicalTreatmentEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MedicalTreatmentDaoImpl extends AbstractDao<MedicalTreatmentEntity, Long> implements MedicalTreatmentDao {
}