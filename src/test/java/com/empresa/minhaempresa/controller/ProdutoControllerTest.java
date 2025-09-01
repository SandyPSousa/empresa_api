package com.empresa.minhaempresa.controller;

import com.empresa.minhaempresa.model.Produto;
import com.empresa.minhaempresa.services.ProdutoService;
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

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void testListarProdutos() throws Exception {
        when(produtoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/produtos"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarProdutoPorId_Existente() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        when(produtoService.findById(1L)).thenReturn(Optional.of(produto));

        mockMvc.perform(get("/api/produtos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarProdutoPorId_NaoExistente() throws Exception {
        when(produtoService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/produtos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCriarProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Teste Produto");
        when(produtoService.save(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testAtualizarProduto_Existente() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        when(produtoService.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoService.save(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(put("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizarProduto_NaoExistente() throws Exception {
        Produto produto = new Produto();
        when(produtoService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/produtos/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletarProduto() throws Exception {
        mockMvc.perform(delete("/api/produtos/1"))
                .andExpect(status().isNoContent());
        verify(produtoService, times(1)).deleteById(1L);
    }
}