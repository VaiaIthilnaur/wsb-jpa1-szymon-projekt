package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.service.impl.VisitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class VisitServiceTest {

    @Mock
    private VisitDao visitDao;

    @InjectMocks
    private VisitServiceImpl visitService;

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
    }

    @Test
    public void testShouldDeleteVisit() {
        // Given
        when(visitDao.findOne(1L)).thenReturn(visitEntity);

        // When
        visitService.delete(1L);

        // Then
        verify(visitDao, times(1)).delete(1L);
    }

    @Test
    public void testShouldFindVisitById() {
        // Given
        when(visitDao.findOne(1L)).thenReturn(visitEntity);

        // When
        VisitTO visitTO = visitService.findById(1L);

        // Then
        assertThat(visitTO).isNotNull();
        assertThat(visitTO.getId()).isEqualTo(1L);
        assertThat(visitTO.getDescription()).isEqualTo("Regular checkup");
    }

    @Test
    public void testFindAllVisitsByPatientId() {
        // Given
        when(visitDao.findByPatientEntityId(1L)).thenReturn(Collections.singletonList(visitEntity));

        // When
        List<VisitTO> visits = visitService.findAllVisitsByPatientId(1L);

        // Then
        assertThat(visits).isNotEmpty();
        assertThat(visits).hasSize(1);
        assertThat(visits.get(0).getDescription()).isEqualTo("Regular checkup");
        verify(visitDao, times(1)).findByPatientEntityId(1L);
    }
}