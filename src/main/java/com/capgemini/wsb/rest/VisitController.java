package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.VisitService;
import org.springframework.web.bind.annotation.*;

@RestController
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visit/{id}")
    VisitTO findById(@PathVariable Long id) {
        VisitTO visit = visitService.findById(id);
        if (visit != null) {
            return visit;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping("/visit")
    VisitTO save(@RequestBody VisitTO visit) {
        return visitService.save(visit);
    }

    @DeleteMapping("/visit/{id}")
    void delete(@PathVariable Long id) {
        visitService.delete(id);
    }
}