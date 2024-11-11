package com.juanito2005.crud_computers_shop.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juanito2005.crud_computers_shop.models.Computer;
import com.juanito2005.crud_computers_shop.models.Store;
import com.juanito2005.crud_computers_shop.repositories.ComputersRepository;
import com.juanito2005.crud_computers_shop.repositories.StoreRepository;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private ComputersRepository computersRepository;

    @InjectMocks
    private StoreService storeService;

    private Store testStore;
    private Computer testComputer;

    @BeforeEach
    void setUp() {
        testStore = new Store();
        testStore.setId(1L);
        testStore.setName("Test Store");

        testComputer = new Computer();
        testComputer.setBrand("Test Brand");
        testComputer.setMemory(16);
        testComputer.setProcessor("Test Processor");
        testComputer.setOperativeSystem("Test OS");
        testComputer.setPrice(1000.0);
    }

    @Test
    void testAddComputer() {
        // Arrange
        when(storeRepository.findById(1L)).thenReturn(Optional.of(testStore));
        when(computersRepository.save(any(Computer.class))).thenReturn(testComputer);

        // Act
        Computer result = storeService.addComputer(1L, testComputer);

        // Assert
        assertNotNull(result);
        assertEquals("Test Brand", result.getBrand());
        assertEquals(testStore, result.getStore());

        verify(storeRepository).findById(1L);
        verify(computersRepository).save(testComputer);
        verify(storeRepository).save(testStore);
    }

    @Test
    void testAddComputerStoreNotFound() {
        // Arrange
        when(storeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> storeService.addComputer(1L, testComputer));

        verify(storeRepository).findById(1L);
        verify(computersRepository, never()).save(any(Computer.class));
        verify(storeRepository, never()).save(any(Store.class));
    }
}