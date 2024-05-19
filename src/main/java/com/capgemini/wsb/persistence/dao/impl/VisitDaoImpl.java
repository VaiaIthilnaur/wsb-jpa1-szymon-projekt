package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.MedicalTreatmentDao;
import com.capgemini.wsb.persistence.dao.VisitDao;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {
}