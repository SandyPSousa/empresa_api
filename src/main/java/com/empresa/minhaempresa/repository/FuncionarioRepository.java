package com.empresa.minhaempresa.repository;


import com.empresa.minhaempresa.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}