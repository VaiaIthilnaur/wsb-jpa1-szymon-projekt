package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.VisitTO;

import java.util.List;

public interface VisitService {
    VisitTO findById(Long id);
    VisitTO save(VisitTO visit);
    void delete(Long id);
}