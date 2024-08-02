package com.br.veiculos.verzel.records;

public record UsuarioRecoverPasswordDTO(
        String email,
        String newPassword,
        String code
) {
}
