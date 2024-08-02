package com.br.veiculos.verzel.repository;

import com.br.veiculos.verzel.model.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {
}
