package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.MedicalTreatmentTO;
import com.capgemini.wsb.persistence.entity.MedicalTreatmentEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import com.capgemini.wsb.service.impl.MedicalTreatmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MedicalTreatmentServiceTest {

    @Mock
    private MedicalTreatmentDao treatmentDao;

    @InjectMocks
    private MedicalTreatmentServiceImpl treatmentService;

    private MedicalTreatmentEntity treatmentEntity;

    @BeforeEach
    public void setUp() {
        // Prepare a MedicalTreatmentEntity
        treatmentEntity = new MedicalTreatmentEntity();
        treatmentEntity.setId(1L);
        treatmentEntity.setDescription("Blood test");
        treatmentEntity.setType(TreatmentType.USG);
    }

    @Test
    public void testShouldDeleteTreatment() {
        // Given
        when(treatmentDao.findOne(1L)).thenReturn(treatmentEntity);

        // When
        treatmentService.delete(1L);

        // Then
        verify(treatmentDao, times(1)).delete(1L);
    }

    @Test
    public void testShouldFindTreatmentById() {
        // Given
        when(treatmentDao.findOne(1L)).thenReturn(treatmentEntity);

        // When
        MedicalTreatmentTO treatmentTO = treatmentService.findById(1L);

        // Then
        assertThat(treatmentTO).isNotNull();
        assertThat(treatmentTO.getId()).isEqualTo(1L);
        assertThat(treatmentTO.getDescription()).isEqualTo("Blood test");
        assertThat(treatmentTO.getType()).isEqualTo(TreatmentType.USG);
    }
}