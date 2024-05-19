package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VisitDao extends Dao<VisitEntity, Long>{

}