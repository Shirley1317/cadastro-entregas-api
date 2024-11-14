package com.example.cadastro_entregas_api.repository;

import com.example.cadastro_entregas_api.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
