package com.br.veiculos.verzel.repository;

import com.br.veiculos.verzel.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    @Query("SELECT u FROM Usuarios u WHERE u.email = :email")
    Usuarios findByUserEmail(@Param("email") String email);
}
