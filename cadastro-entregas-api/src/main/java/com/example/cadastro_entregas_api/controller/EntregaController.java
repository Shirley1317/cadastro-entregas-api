package com.example.cadastro_entregas_api.controller;

import com.example.cadastro_entregas_api.model.Entrega;
import com.example.cadastro_entregas_api.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaRepository entregaRepository;

    @PostMapping
    public Entrega cadastrarEntrega(@RequestBody Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> consultarEntrega(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> atualizarEntrega(@PathVariable Long id, @RequestBody Entrega entregaAtualizada) {
        return entregaRepository.findById(id)
                .map(entrega -> {
                    entrega.setQuantidadePacotes(entregaAtualizada.getQuantidadePacotes());
                    entrega.setDataLimiteEntrega(entregaAtualizada.getDataLimiteEntrega());
                    entrega.setNomeCliente(entregaAtualizada.getNomeCliente());
                    entrega.setCpfCliente(entregaAtualizada.getCpfCliente());
                    entrega.setCep(entregaAtualizada.getCep());
                    entrega.setUf(entregaAtualizada.getUf());
                    entrega.setCidade(entregaAtualizada.getCidade());
                    entrega.setBairro(entregaAtualizada.getBairro());
                    entrega.setRua(entregaAtualizada.getRua());
                    entrega.setNumero(entregaAtualizada.getNumero());
                    entrega.setComplemento(entregaAtualizada.getComplemento());
                    return ResponseEntity.ok(entregaRepository.save(entrega));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEntrega(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> {
                    entregaRepository.delete(entrega);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll();
    }
}
