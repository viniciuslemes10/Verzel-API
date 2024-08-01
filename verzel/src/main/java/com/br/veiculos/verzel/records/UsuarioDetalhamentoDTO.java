package com.br.veiculos.verzel.records;

import com.br.veiculos.verzel.model.Usuarios;

public record UsuarioDetalhamentoDTO(
        String email,
        String nomeCompleto,
        String senha,
        Boolean contaNaoExpirada,
        Boolean contaNaoBloqueada,
        Boolean credenciaisNaoExpiradas,
        Boolean habilitado
) {
    public UsuarioDetalhamentoDTO(Usuarios usuarios) {
        this(usuarios.getEmail(), usuarios.getNomeCompleto(), usuarios.getSenha(), usuarios.getContaNaoExpirada(),
        usuarios.getContaNaoBloqueada(), usuarios.getCredenciaisNaoExpiradas(), usuarios.getHabilitado());
    }
}
