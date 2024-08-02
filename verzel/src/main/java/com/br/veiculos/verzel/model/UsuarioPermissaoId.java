package com.br.veiculos.verzel.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;
@Embeddable
public class UsuarioPermissaoId {
    private Long idUsuario;
    private Long idPermissao;

    public UsuarioPermissaoId() {}

    public UsuarioPermissaoId(Long idUsuario, Long idPermissao) {
        this.idUsuario = idUsuario;
        this.idPermissao = idPermissao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Long idPermissao) {
        this.idPermissao = idPermissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioPermissaoId that)) return false;
        return Objects.equals(idUsuario, that.idUsuario) &&
                Objects.equals(idPermissao, that.idPermissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idPermissao);
    }
}
