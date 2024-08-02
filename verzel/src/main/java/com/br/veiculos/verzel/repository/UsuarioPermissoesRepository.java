package com.br.veiculos.verzel.repository;

import com.br.veiculos.verzel.model.UsuarioPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPermissoesRepository extends JpaRepository<UsuarioPermissao, Long> {
}
