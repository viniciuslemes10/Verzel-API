package com.br.veiculos.verzel.records;

import com.br.veiculos.verzel.model.Veiculos;

import java.math.BigDecimal;

public record VeiculosDetalhamentoDTO(
        Long id,
        String nome,
        String marca,
        String modelo,
        BigDecimal valor,
        String foto
) {
    public VeiculosDetalhamentoDTO(Veiculos veiculo) {
        this(veiculo.getId(), veiculo.getNome(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getValor(), veiculo.getFoto());
    }
}
