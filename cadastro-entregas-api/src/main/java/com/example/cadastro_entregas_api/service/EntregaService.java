package com.example.cadastro_entregas_api.service;

import com.example.cadastro_entregas_api.model.Entrega;
import com.example.cadastro_entregas_api.repository.EntregaRepository;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega salvarEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public Optional<Entrega> buscarEntregaPorId (Long id) {
        return entregaRepository.findById(id);
    }

    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> atualizarEntrega(Long id, Entrega entregaAtualizada) {
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
                    return entregaRepository.save(entrega);
                });
    }
    public boolean excluirEntrega(Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> {
                    entregaRepository.delete(entrega);
                    return true;
                }).orElse(false);
    }

    public List<Entrega> listarTodasEntregas() {
        return entregaRepository.findAll();
    }
}
