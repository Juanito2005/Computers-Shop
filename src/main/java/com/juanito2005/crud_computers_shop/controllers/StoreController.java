package com.juanito2005.crud_computers_shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanito2005.crud_computers_shop.models.Computer;
import com.juanito2005.crud_computers_shop.services.StoreService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/store")
@Slf4j
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/{storeId}/computers")
    public ResponseEntity<Computer> addComputer(@PathVariable Long storeId, @RequestBody Computer computer) {
        log.info("Request received to add computer to store with id: {}," + storeId);
        Computer computeradded = storeService.addComputer(storeId, computer);
        return new ResponseEntity<>(computeradded, HttpStatus.CREATED);
    }

}
