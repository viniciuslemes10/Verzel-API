package com.br.veiculos.verzel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PermissaoNotFoundException extends RuntimeException {
    public PermissaoNotFoundException(String message) {
        super(message);
    }
}
