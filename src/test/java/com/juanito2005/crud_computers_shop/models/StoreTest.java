package com.juanito2005.crud_computers_shop.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class StoreTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store();
    }

    @Test
    void testStoreCreation() {
        assertNotNull(store);
    }

    @Test
    void testStoreProperties() {
        Long id = 1L;
        String name = "Test Store";
        String owner = "John Doe";
        String storeTaxId = "123456789";

        store.setId(id);
        store.setName(name);
        store.setOwner(owner);
        store.setStoreTaxId(storeTaxId);

        assertEquals(id, store.getId());
        assertEquals(name, store.getName());
        assertEquals(owner, store.getOwner());
        assertEquals(storeTaxId, store.getStoreTaxId());
    }

    @Test
    void testComputersList() {
        assertTrue(store.getComputers().isEmpty());

        Computer computer = new Computer();
        computer.setBrand("Test Brand");
        store.getComputers().add(computer);

        assertFalse(store.getComputers().isEmpty());
        assertEquals(1, store.getComputers().size());
        assertEquals("Test Brand", store.getComputers().get(0).getBrand());
    }

    @Test
    void testEqualsAndHashCode() {
        Store store1 = new Store(1L, "Store 1", "Owner 1", "Tax1", new ArrayList<>());
        Store store2 = new Store(1L, "Store 1", "Owner 1", "Tax1", new ArrayList<>());
        Store store3 = new Store(2L, "Store 2", "Owner 2", "Tax2", new ArrayList<>());

        assertEquals(store1, store2);
        assertNotEquals(store1, store3);
        assertEquals(store1.hashCode(), store2.hashCode());
        assertNotEquals(store1.hashCode(), store3.hashCode());
    }

    @Test
    void testToString() {
        Store testStore = new Store(1L, "Test Store", "Test Owner", "123456", new ArrayList<>());
        String toString = testStore.toString();

        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name=Test Store"));
        assertTrue(toString.contains("owner=Test Owner"));
        assertTrue(toString.contains("storeTaxId=123456"));
    }
}
