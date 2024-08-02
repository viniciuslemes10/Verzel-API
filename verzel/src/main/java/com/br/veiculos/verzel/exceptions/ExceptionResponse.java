package com.br.veiculos.verzel.exceptions;

import java.util.Date;

public class ExceptionResponse {
    private Date data;
    private String mensagem;
    private String detalhes;

    public ExceptionResponse(Date data, String mensagem, String detalhes) {
        this.data = data;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public Date getData() {
        return data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }
}
