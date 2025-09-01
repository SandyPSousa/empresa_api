package com.empresa.minhaempresa.repository;


import com.empresa.minhaempresa.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}