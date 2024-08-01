package com.br.veiculos.verzel.records.vo;

import java.util.Date;
import java.util.Objects;

public class TokenVO {
    private String email;
    private Boolean autenticado;
    private Date criado;
    private Date expirado;
    private String accessToken;
    private String refreshToken;

    public TokenVO(){}

    public TokenVO(String email,
                   Boolean autenticado,
                   Date criado,
                   Date expirado,
                   String accessToken,
                   String refreshToken) {
        this.email = email;
        this.autenticado = autenticado;
        this.criado = criado;
        this.expirado = expirado;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenVO tokenVO)) return false;
        return Objects.equals(email, tokenVO.email) && Objects.equals(autenticado, tokenVO.autenticado) && Objects.equals(criado, tokenVO.criado) && Objects.equals(expirado, tokenVO.expirado) && Objects.equals(accessToken, tokenVO.accessToken) && Objects.equals(refreshToken, tokenVO.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, autenticado, criado, expirado, accessToken, refreshToken);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public Date getCriado() {
        return criado;
    }

    public void setCriado(Date criado) {
        this.criado = criado;
    }

    public Date getExpirado() {
        return expirado;
    }

    public void setExpirado(Date expirado) {
        this.expirado = expirado;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
