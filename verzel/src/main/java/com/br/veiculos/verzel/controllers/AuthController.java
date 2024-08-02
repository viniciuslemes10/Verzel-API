package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.records.vo.AccountCredentialsVO;
import com.br.veiculos.verzel.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Auth", description = "Endpoints para Autheticação de usuários")
public class AuthController {
    @Autowired
    private AuthService service;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Autenticação de Usuário",
            description = "Informe o e-mail e a senha cadastrados para autenticar o usuário. A resposta incluirá as credenciais do usuário, como accessToken, refreshToken, e informações do usuário.",

            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity login(@RequestBody AccountCredentialsVO data) {
        if(vericarSeParametroNotNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        var token = service.login(data);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    private boolean vericarSeParametroNotNull(AccountCredentialsVO data) {
        return data == null || data.getEmail() == null || data.getEmail().isBlank()
                || data.getSenha() == null || data.getSenha().isBlank();
    }
}
