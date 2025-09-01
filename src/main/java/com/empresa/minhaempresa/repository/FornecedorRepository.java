package com.empresa.minhaempresa.repository;


import com.empresa.minhaempresa.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}