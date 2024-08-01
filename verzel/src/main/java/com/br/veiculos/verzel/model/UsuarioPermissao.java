package com.br.veiculos.verzel.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuario_permissao")
public class UsuarioPermissao {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_permissao")
    private Permissoes permissao;

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
