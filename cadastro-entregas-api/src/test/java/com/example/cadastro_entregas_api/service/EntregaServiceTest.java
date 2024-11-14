package com.example.cadastro_entregas_api.service;

import com.example.cadastro_entregas_api.model.Entrega;
import com.example.cadastro_entregas_api.repository.EntregaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private EntregaService entregaService;

    @Test
    public void deveSalvarEntregaComSucesso() {
        Entrega entrega = new Entrega();
        entrega.setNomeCliente("Cliente Teste");

        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);

        Entrega entregaSalva = entregaService.salvarEntrega(entrega);

        assertNotNull(entregaSalva);
        assertEquals("Cliente Teste", entregaSalva.getNomeCliente());
    }

    @Test
    public void deveRetornarEntregaPorId() {
        Entrega entrega = new Entrega();
        entrega.setId(1L);

        when(entregaRepository.findById(1L)).thenReturn(Optional.of(entrega));

        Optional<Entrega> entregaEncontrada = entregaService.buscarEntregaPorId(1L);

        assertTrue(entregaEncontrada.isPresent());
        assertEquals(1L, entregaEncontrada.get().getId());
    }

    @Test
    public void deveExcluirEntregaPorId() {
        Entrega entrega = new Entrega();
        entrega.setId(1L);

        when(entregaRepository.findById(1L)).thenReturn(Optional.of(entrega));

        boolean resultado = entregaService.excluirEntrega(1L);

        assertTrue(resultado);
        Mockito.verify(entregaRepository).delete(entrega);
    }
}
