package com.empresa.minhaempresa.controller;

import com.empresa.minhaempresa.model.Departamento;
import com.empresa.minhaempresa.services.DepartamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartamentoController.class)
public class DepartamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartamentoService departamentoService;

    @Test
    void testListarDepartamentos() throws Exception {
        when(departamentoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/departamentos"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarDepartamentoPorId_Existente() throws Exception {
        Departamento departamento = new Departamento();
        departamento.setId(1L);
        when(departamentoService.findById(1L)).thenReturn(Optional.of(departamento));

        mockMvc.perform(get("/api/departamentos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarDepartamentoPorId_NaoExistente() throws Exception {
        when(departamentoService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/departamentos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCriarDepartamento() throws Exception {
        Departamento departamento = new Departamento();
        departamento.setNome("TI");
        when(departamentoService.save(any(Departamento.class))).thenReturn(departamento);

        mockMvc.perform(post("/api/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isCreated());
    }

    @Test
    void testAtualizarDepartamento_Existente() throws Exception {
        Departamento departamento = new Departamento();
        departamento.setId(1L);
        when(departamentoService.findById(1L)).thenReturn(Optional.of(departamento));
        when(departamentoService.save(any(Departamento.class))).thenReturn(departamento);

        mockMvc.perform(put("/api/departamentos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizarDepartamento_NaoExistente() throws Exception {
        Departamento departamento = new Departamento();
        when(departamentoService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/departamentos/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletarDepartamento() throws Exception {
        mockMvc.perform(delete("/api/departamentos/1"))
                .andExpect(status().isNoContent());
        verify(departamentoService, times(1)).deleteById(1L);
    }
}