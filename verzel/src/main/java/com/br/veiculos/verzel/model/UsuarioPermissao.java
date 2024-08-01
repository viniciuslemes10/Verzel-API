package com.br.veiculos.verzel.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuario_permissao")
public class UsuarioPermissao {
    @EmbeddedId
    private UsuarioPermissaoId id;

    @MapsId("idUsuario")
    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuarios usuario;

    @MapsId("idPermissao")
    @ManyToOne
    @JoinColumn(name = "id_permissao", insertable = false, updatable = false)
    private Permissoes permissao;

    public UsuarioPermissao() {}

    public UsuarioPermissao(Usuarios user, Permissoes permissoes) {
        this.usuario = user;
        this.permissao = permissoes;
    }

    public UsuarioPermissaoId getId() {
        return id;
    }

    public void setId(UsuarioPermissaoId id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Permissoes getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissoes permissao) {
        this.permissao = permissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioPermissao that)) return false;
        return Objects.equals(usuario, that.usuario) && Objects.equals(permissao, that.permissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, permissao);
    }
}
