package com.juanito2005.crud_computers_shop.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComputerTest {

    private Computer computer;
    private Store store;

    @BeforeEach
    void setUp() {
        computer = new Computer();
        store = new Store();
    }

    @Test
    void testComputerCreation() {
        assertNotNull(computer);
    }

    @Test
    void testComputerProperties() {
        Long id = 1L;
        String brand = "Mac";
        int memory = 512;
        String processor = "M4";
        String operativeSystem = "IOS";
        double price = 1000.0;

        computer.setBrand(brand);
        computer.setId(id);
        computer.setMemory(memory);
        computer.setPrice(price);
        computer.setOperativeSystem(operativeSystem);
        computer.setProcessor(processor);

        assertEquals(id, computer.getId());
        assertEquals(brand, computer.getBrand());
        assertEquals(memory, computer.getMemory());
        assertEquals(price, computer.getPrice());
        assertEquals(operativeSystem, computer.getOperativeSystem());
        assertEquals(processor, computer.getProcessor());
    }
    @Test
    void testStoreAssociation() {
        computer.setStore(store);
        assertEquals(store, computer.getStore());
    }
}
