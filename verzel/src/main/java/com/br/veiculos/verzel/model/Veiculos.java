package com.br.veiculos.verzel.model;

import com.br.veiculos.verzel.records.VeiculosDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "veiculos")
public class Veiculos  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String marca;

    private String modelo;

    private String foto;

    private BigDecimal valor;

    public Veiculos() {}

    public Veiculos(VeiculosDTO data) {
        this.nome = data.nome();
        this.marca = data.marca();
        this.modelo = data.modelo();
        this.valor = data.valor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculos veiculos)) return false;
        return Objects.equals(id, veiculos.id) && Objects.equals(nome, veiculos.nome) && Objects.equals(marca, veiculos.marca) && Objects.equals(modelo, veiculos.modelo) && Objects.equals(foto, veiculos.foto) && Objects.equals(valor, veiculos.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, marca, modelo, foto, valor);
    }

    @Override
    public String toString() {
        return "Veiculos{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", foto='" + foto + '\'' +
                ", valor=" + valor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
