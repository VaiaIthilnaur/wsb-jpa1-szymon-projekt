package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PatientDaoTest {

    /*
    Poniższe testy sprawdzają trzy różne zapytania dla DAO:

	1.	Znajdowanie pacjentów po nazwisku: Metoda findByLastName sprawdza, czy można znaleźć pacjentów na podstawie ich nazwiska.
	2.	Znajdowanie pacjentów, którzy mieli więcej niż X wizyt: Metoda findByVisitsGreaterThan sprawdza, czy można znaleźć pacjentów, którzy mieli więcej niż określoną liczbę wizyt.
	3.	Znajdowanie pacjentów po dacie urodzenia: Metoda findByDateOfBirthAfter sprawdza, czy można znaleźć pacjentów, którzy urodzili się po określonej dacie.

    */

    @Autowired
    private PatientDao patientDao;

    private PatientEntity patientEntity;

    @BeforeEach
    public void setUp() {
        // Prepare a PatientEntity
        patientEntity = new PatientEntity();
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");
        patientEntity.setTelephoneNumber("123456789");
        patientEntity.setEmail("john.doe@example.com");
        patientEntity.setPatientNumber("PAT123");
        patientEntity.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patientDao.save(patientEntity);

        // Prepare a VisitEntity and assign to PatientEntity
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setDescription("Checkup");
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setPatientEntity(patientEntity);
        patientEntity.setVisitEntities(Collections.singletonList(visitEntity));
        patientDao.save(patientEntity);
    }

    @Test
    public void testFindByLastName() {
        // When
        List<PatientEntity> patients = patientDao.findByLastName("Doe");

        // Then
        assertThat(patients).isNotEmpty();
        assertThat(patients.get(0).getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testFindByVisitsGreaterThan() {
        // Given
        int numberOfVisits = 0;

        // When
        List<PatientEntity> patients = patientDao.findByVisitsGreaterThan(numberOfVisits);

        // Then
        assertThat(patients).isNotEmpty();
        assertThat(patients.get(0).getVisitEntities()).hasSizeGreaterThan(numberOfVisits);
    }

    @Test
    public void testFindByDateOfBirthAfter() {
        // When
        List<PatientEntity> patients = patientDao.findByDateOfBirthAfter(LocalDate.of(1989, 12, 31));

        // Then
        assertThat(patients).isNotEmpty();
        assertThat(patients.get(0).getDateOfBirth()).isAfter(LocalDate.of(1989, 12, 31));
    }

}

