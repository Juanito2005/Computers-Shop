package com.juanito2005.crud_computers_shop.repositories;

import com.juanito2005.crud_computers_shop.models.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputersRepository extends JpaRepository<Computer, Long> {}
