package com.juanito2005.crud_computers_shop.services;

import org.springframework.stereotype.Service;

import com.juanito2005.crud_computers_shop.models.Computer;
import com.juanito2005.crud_computers_shop.models.Store;
import com.juanito2005.crud_computers_shop.repositories.ComputersRepository;
import com.juanito2005.crud_computers_shop.repositories.StoreRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final ComputersRepository computersRepository;

    public StoreService(StoreRepository storeRepository, ComputersRepository computersRepository) {
        this.storeRepository = storeRepository;
        this.computersRepository = computersRepository;
    }

    public Computer addComputer(Long storeId, Computer computer) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new RuntimeException("store not found with id: " + storeId));

        computer.setStore(store);
        Computer computerSaved = computersRepository.save(computer);

        store.getComputers().add(computerSaved);
        storeRepository.save(store);

        log.info("Computer added to the store with id: {}", storeId);

        return computerSaved;
    }
}
