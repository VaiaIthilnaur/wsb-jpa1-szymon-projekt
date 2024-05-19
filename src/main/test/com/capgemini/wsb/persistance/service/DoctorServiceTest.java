package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.mapper.DoctorMapper;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import com.capgemini.wsb.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorDao doctorDao;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    private DoctorEntity doctorEntity;

    @BeforeEach
    public void setUp() {
        // Prepare a DoctorEntity
        doctorEntity = new DoctorEntity();
        doctorEntity.setId(1L);
        doctorEntity.setFirstName("John");
        doctorEntity.setLastName("Doe");
        doctorEntity.setTelephoneNumber("123456789");
        doctorEntity.setEmail("john.doe@example.com");
        doctorEntity.setDoctorNumber("DOC123");
        doctorEntity.setSpecialization(Specialization.GP);
    }

    @Test
    public void testShouldDeleteDoctor() {
        // Given
        when(doctorDao.findOne(1L)).thenReturn(doctorEntity);

        // When
        doctorService.delete(1L);

        // Then
        verify(doctorDao, times(1)).delete(1L);
    }

    @Test
    public void testShouldFindDoctorById() {
        // Given
        when(doctorDao.findOne(1L)).thenReturn(doctorEntity);

        // When
        DoctorTO doctorTO = doctorService.findById(1L);

        // Then
        assertThat(doctorTO).isNotNull();
        assertThat(doctorTO.getId()).isEqualTo(1L);
        assertThat(doctorTO.getFirstName()).isEqualTo("John");
        assertThat(doctorTO.getLastName()).isEqualTo("Doe");
        assertThat(doctorTO.getTelephoneNumber()).isEqualTo("123456789");
        assertThat(doctorTO.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(doctorTO.getDoctorNumber()).isEqualTo("DOC123");
        assertThat(doctorTO.getSpecialization()).isEqualTo(Specialization.GP);
    }