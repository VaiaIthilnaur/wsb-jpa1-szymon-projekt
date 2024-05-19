package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.AddressTO;
import com.capgemini.wsb.persistence.dao.AddressDao;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressDao addressDao;

    @InjectMocks
    private AddressServiceImpl addressService;

    private AddressEntity addressEntity;

    @BeforeEach
    public void setUp() {
        // Prepare an AddressEntity
        addressEntity = new AddressEntity();
        addressEntity.setId(1L);
        addressEntity.setCity("City");
        addressEntity.setAddressLine1("Address Line 1");
        addressEntity.setAddressLine2("Address Line 2");
        addressEntity.setPostalCode("12345");
    }

    @Test
    public void testShouldDeleteAddress() {
        // Given
        when(addressDao.findOne(1L)).thenReturn(addressEntity);

        // When
        addressService.delete(1L);

        // Then
        verify(addressDao, times(1)).delete(1L);
    }

    @Test
    public void testShouldFindAddressById() {
        // Given
        when(addressDao.findOne(1L)).thenReturn(addressEntity);

        // When
        AddressTO addressTO = addressService.findById(1L);

        // Then
        assertThat(addressTO).isNotNull();
        assertThat(addressTO.getId()).isEqualTo(1L);
        assertThat(addressTO.getCity()).isEqualTo("City");
        assertThat(addressTO.getAddressLine1()).isEqualTo("Address Line 1");
        assertThat(addressTO.getAddressLine2()).isEqualTo("Address Line 2");
        assertThat(addressTO.getPostalCode()).isEqualTo("12345");
    }
}