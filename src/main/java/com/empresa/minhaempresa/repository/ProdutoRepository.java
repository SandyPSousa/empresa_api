package com.empresa.minhaempresa.repository;

import com.empresa.minhaempresa.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}