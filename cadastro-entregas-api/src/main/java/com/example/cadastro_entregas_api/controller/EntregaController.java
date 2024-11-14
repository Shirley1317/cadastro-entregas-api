package com.example.cadastro_entregas_api.controller;


import com.example.cadastro_entregas_api.model.Entrega;
import com.example.cadastro_entregas_api.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping
    public Entrega cadastrarEntrega(@RequestBody Entrega entrega) {
        return entregaService.salvarEntrega(entrega);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> consultarEntrega(@PathVariable Long id) {
        return entregaService.buscarEntregaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> atualizarEntrega(@PathVariable Long id, @RequestBody Entrega entregaAtualizada) {
        return entregaService.atualizarEntrega(id, entregaAtualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEntrega(@PathVariable Long id) {
        return entregaService.excluirEntrega(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Entrega> listarEntregas() {
        return entregaService.listarEntregas();
    }
}
