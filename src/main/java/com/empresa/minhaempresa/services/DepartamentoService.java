package com.empresa.minhaempresa.services;

import com.empresa.minhaempresa.model.Departamento;
import com.empresa.minhaempresa.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento save(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    public void deleteById(Long id) {
        departamentoRepository.deleteById(id);
    }
}