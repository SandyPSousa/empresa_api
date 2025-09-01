package com.empresa.minhaempresa.controller;

import com.empresa.minhaempresa.model.Fornecedor;
import com.empresa.minhaempresa.services.FornecedorService;
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

@WebMvcTest(FornecedorController.class)
public class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FornecedorService fornecedorService;

    @Test
    void testListarFornecedores() throws Exception {
        when(fornecedorService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/fornecedores"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarFornecedorPorId_Existente() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);
        when(fornecedorService.findById(1L)).thenReturn(Optional.of(fornecedor));

        mockMvc.perform(get("/api/fornecedores/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarFornecedorPorId_NaoExistente() throws Exception {
        when(fornecedorService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/fornecedores/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCriarFornecedor() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Teste Fornecedor");
        when(fornecedorService.save(any(Fornecedor.class))).thenReturn(fornecedor);

        mockMvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fornecedor)))
                .andExpect(status().isCreated());
    }

    @Test
    void testAtualizarFornecedor_Existente() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1L);
        when(fornecedorService.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(fornecedorService.save(any(Fornecedor.class))).thenReturn(fornecedor);

        mockMvc.perform(put("/api/fornecedores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fornecedor)))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizarFornecedor_NaoExistente() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        when(fornecedorService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/fornecedores/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fornecedor)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletarFornecedor() throws Exception {
        mockMvc.perform(delete("/api/fornecedores/1"))
                .andExpect(status().isNoContent());
        verify(fornecedorService, times(1)).deleteById(1L);
    }
}