package com.empresa.minhaempresa.controller;

import com.empresa.minhaempresa.model.Funcionario;
import com.empresa.minhaempresa.services.FuncionarioService;
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

@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FuncionarioService funcionarioService;

    @Test
    void testListarFuncionarios() throws Exception {
        when(funcionarioService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/funcionarios"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarFuncionarioPorId_Existente() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        when(funcionarioService.findById(1L)).thenReturn(Optional.of(funcionario));

        mockMvc.perform(get("/api/funcionarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarFuncionarioPorId_NaoExistente() throws Exception {
        when(funcionarioService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/funcionarios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCriarFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Teste");
        when(funcionarioService.save(any(Funcionario.class))).thenReturn(funcionario);

        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isCreated());
    }

    @Test
    void testAtualizarFuncionario_Existente() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        when(funcionarioService.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioService.save(any(Funcionario.class))).thenReturn(funcionario);

        mockMvc.perform(put("/api/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizarFuncionario_NaoExistente() throws Exception {
        Funcionario funcionario = new Funcionario();
        when(funcionarioService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/funcionarios/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletarFuncionario() throws Exception {
        mockMvc.perform(delete("/api/funcionarios/1"))
                .andExpect(status().isNoContent());
        verify(funcionarioService, times(1)).deleteById(1L);
    }
}