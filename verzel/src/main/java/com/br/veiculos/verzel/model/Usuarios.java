package com.br.veiculos.verzel.model;

import com.br.veiculos.verzel.records.UsuarioDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuarios implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "conta_nao_expirada")
    private Boolean contaNaoExpirada;

    @Column(name = "conta_nao_bloqueada")
    private Boolean contaNaoBloqueada;

    @Column(name = "credenciais_nao_expiradas")
    private Boolean credenciaisNaoExpiradas;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_permissao",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao")
    )
    private List<Permissoes> permissoes;

    public Usuarios() {}

    public Usuarios(UsuarioDTO data) {
        this.email = data.email();
        this.nomeCompleto = data.nomeCompleto();
        this.contaNaoExpirada = true;
        this.contaNaoBloqueada = true;
        this.credenciaisNaoExpiradas = true;
        this.habilitado = true;
    }

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permissoes permissoes : this.permissoes) {
            roles.add(permissoes.getDescricao());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.contaNaoExpirada;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.contaNaoBloqueada;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credenciaisNaoExpiradas;
    }

    @Override
    public boolean isEnabled() {
        return this.habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuarios usuarios)) return false;
        return Objects.equals(id, usuarios.id) && Objects.equals(email, usuarios.email) && Objects.equals(nomeCompleto, usuarios.nomeCompleto) && Objects.equals(senha, usuarios.senha) && Objects.equals(contaNaoExpirada, usuarios.contaNaoExpirada) && Objects.equals(contaNaoBloqueada, usuarios.contaNaoBloqueada) && Objects.equals(credenciaisNaoExpiradas, usuarios.credenciaisNaoExpiradas) && Objects.equals(habilitado, usuarios.habilitado) && Objects.equals(permissoes, usuarios.permissoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, nomeCompleto, senha, contaNaoExpirada, contaNaoBloqueada, credenciaisNaoExpiradas, habilitado, permissoes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getContaNaoExpirada() {
        return contaNaoExpirada;
    }

    public void setContaNaoExpirada(Boolean contaNaoExpirada) {
        this.contaNaoExpirada = contaNaoExpirada;
    }

    public Boolean getContaNaoBloqueada() {
        return contaNaoBloqueada;
    }

    public void setContaNaoBloqueada(Boolean contaNaoBloqueada) {
        this.contaNaoBloqueada = contaNaoBloqueada;
    }

    public Boolean getCredenciaisNaoExpiradas() {
        return credenciaisNaoExpiradas;
    }

    public void setCredenciaisNaoExpiradas(Boolean credenciaisNaoExpiradas) {
        this.credenciaisNaoExpiradas = credenciaisNaoExpiradas;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<Permissoes> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissoes> permissoes) {
        this.permissoes = permissoes;
    }
}
