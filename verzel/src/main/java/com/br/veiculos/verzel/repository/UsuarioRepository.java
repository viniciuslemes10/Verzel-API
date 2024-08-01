package com.br.veiculos.verzel.repository;

import com.br.veiculos.verzel.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
}
