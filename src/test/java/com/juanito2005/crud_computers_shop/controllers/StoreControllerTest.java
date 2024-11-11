package com.juanito2005.crud_computers_shop.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanito2005.crud_computers_shop.models.Computer;
import com.juanito2005.crud_computers_shop.services.StoreService;

@WebMvcTest(StoreController.class)
public class StoreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Computer tetsComputer;

    @BeforeEach
    void setUp() {
        tetsComputer = new Computer();
        tetsComputer.setBrand("Macbook");
        tetsComputer.setMemory(256);
        tetsComputer.setProcessor("M4");
        tetsComputer.setOperativeSystem("IOS");
        tetsComputer.setPrice(1000.0);
    }

    @Test
    void testAddComputer() throws Exception {
        Long storedId = 1L;
        when(storeService.addComputer(eq(storedId),any(Computer.class))).thenReturn(tetsComputer);

        mockMvc.perform(post("/store/{storeId}/computers", storedId).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(tetsComputer)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.brand").value("Macbook"))
            .andExpect(jsonPath("$.memory").value(256))
            .andExpect(jsonPath("$.processor").value("M4"))
            .andExpect(jsonPath("$.operativeSystem").value("IOS"))
            .andExpect(jsonPath("$.price").value(1000.0));
        
        verify(storeService).addComputer(eq(storedId), any(Computer.class));

    }
}
