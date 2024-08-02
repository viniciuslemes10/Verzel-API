package com.br.veiculos.verzel.records;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record VeiculosDTO(
        String nome,
        String marca,
        String modelo,
        BigDecimal valor,
        MultipartFile foto
) {
}
