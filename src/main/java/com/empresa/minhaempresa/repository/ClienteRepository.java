package com.empresa.minhaempresa.repository;

import com.empresa.minhaempresa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}