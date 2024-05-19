package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import com.capgemini.wsb.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PatientServiceTest {

    @Mock
    private PatientDao patientDao;

    @InjectMocks
    private PatientServiceImpl patientService;

    private PatientEntity patientEntity;
    private VisitEntity visitEntity;

    @BeforeEach
    public void setUp() {
        // Prepare a PatientEntity
        patientEntity = new PatientEntity();
        patientEntity.setId(1L);
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");
        patientEntity.setTelephoneNumber("123456789");
        patientEntity.setEmail("john.doe@example.com");
        patientEntity.setPatientNumber("PAT123");
        patientEntity.setDateOfBirth(LocalDate.of(1990, 1, 1));

        // Prepare a VisitEntity
        visitEntity = new VisitEntity();
        visitEntity.setId(1L);
        visitEntity.setDescription("Regular checkup");
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setPatientEntity(patientEntity);

        // Assign VisitEntity to PatientEntity
        patientEntity.setVisitEntities(Collections.singletonList(visitEntity));
    }

    @Test
    public void testShouldDeletePatientAndCascadingVisits() {
        // Given
        when(patientDao.findOne(1L)).thenReturn(patientEntity);

        // When
        patientService.delete(1L);

        // Then
        verify(patientDao, times(1)).delete(1L);
        assertThat(patientEntity.getVisitEntities()).isEmpty(); // Ensure visits are deleted
        verify(patientDao, never()).delete(any()); // Ensure doctors are not deleted
    }

    @Test
    public void testShouldFindPatientById() {
        // Given
        when(patientDao.findOne(1L)).thenReturn(patientEntity);

        // When
        PatientTO patientTO = patientService.findById(1L);

        // Then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(1L);
        assertThat(patientTO.getFirstName()).isEqualTo("John");
        assertThat(patientTO.getLastName()).isEqualTo("Doe");
        assertThat(patientTO.getTelephoneNumber()).isEqualTo("123456789");
        assertThat(patientTO.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(patientTO.getPatientNumber()).isEqualTo("PAT123");
        assertThat(patientTO.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(patientTO.getVisits()).hasSize(1);
        assertThat(patientTO.getVisits().get(0).getDescription()).isEqualTo("Regular checkup");
    }
}