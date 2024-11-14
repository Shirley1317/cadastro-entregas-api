package com.example.cadastro_entregas_api.controller;

import com.example.cadastro_entregas_api.model.Entrega;
import com.example.cadastro_entregas_api.service.EntregaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EntregaController.class)
public class EntregaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntregaService entregaService;

    private Entrega entrega;

    @BeforeEach
    public void setUp() {
        entrega = new Entrega();
        entrega.setId(1L);
        entrega.setNomeCliente("Cliente Teste");
        entrega.setQuantidadePacotes(3);
        entrega.setDataLimiteEntrega(LocalDate.now());
        entrega.setCep("03432-000");

    }

    @Test
    public void deveCadastrarEntregaComSucesso() throws Exception {
        when(entregaService.salvarEntrega(any(Entrega.class))).thenReturn(entrega);

        mockMvc.perform(post("/entregas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nomeCliente\":\"Cliente Teste\",\"quantidadePacotes\":3,\"dataLimiteEntrega\":\"2025-12-01\",\"cep\":\"03432-000\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCliente").value("Cliente Teste"))
                .andExpect(jsonPath("$.quantidadePacotes").value(3));

    }

    @Test
    public void deveConsultarEntregaPorId() throws Exception {
       when(entregaService.buscarEntregaPorId(1L)).thenReturn(Optional.of(entrega));

       mockMvc.perform(get("/entrega/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.nomeCliente").value("Cliente Teste"));
    }

    @Test
    public void deveRetornarFoundAoConsultarEntregaInexistente() throws Exception {
        when(entregaService.buscarEntregaPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/entregas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveExcluirEntregaComSucesso() throws Exception {
        when(entregaService.excluirEntrega(1L)).thenReturn(true);

        mockMvc.perform(delete("/entregas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveListarEntregas() throws  Exception {
        when(entregaService.listarTodasEntregas()).thenReturn(Arrays.asList(entrega));

        mockMvc.perform(get("/entregas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeCliente").value("Cliente Teste"));

    }
}
