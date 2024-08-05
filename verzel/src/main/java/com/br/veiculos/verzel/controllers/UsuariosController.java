package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.UsuarioDTO;
import com.br.veiculos.verzel.records.UsuarioDetalhamentoDTO;
import com.br.veiculos.verzel.records.UsuarioPasswordDTO;
import com.br.veiculos.verzel.records.UsuarioRecoverPasswordDTO;
import com.br.veiculos.verzel.services.UsuarioPermissoesService;
import com.br.veiculos.verzel.services.UsuariosService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("register/users")
@Tag(name = "Usuários", description = "Endpoints exclusivos para usuários")
public class UsuariosController {
    @Autowired
    private UsuariosService service;

    @Autowired
    private UsuarioPermissoesService usuarioPermissoesService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criando um Usuário", description = "Add novo Usuário passado em JSON, representando um usuário",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsuarioDetalhamentoDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<UsuarioDetalhamentoDTO> createUser(@RequestBody UsuarioDTO data) {
        var user = service.createUser(data);
        usuarioPermissoesService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDetalhamentoDTO(user));
    }

    @PostMapping("/enviar-codigo")
    @Operation(summary = "Enviando Email para o Usuário", description = "Informe seu e-mail para receber um código de recuperação de senha. Você usará este código para redefinir sua senha perdida",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = String.class
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<String> sendCode(@RequestBody UsuarioPasswordDTO data) {
        var usuario = service.sendCode(data);
        return ResponseEntity.ok("Email enviado com sucesso para: " + usuario.getEmail());
    }

    @PostMapping("/redefinir-senha")
    @Operation(summary = "Recuperação de Senha", description = "Para redefinir sua senha, insira o e-mail cadastrado, o código enviado para esse e-mail e a nova senha desejada.",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsuarioDetalhamentoDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<UsuarioDetalhamentoDTO> recoverPassword(@RequestBody UsuarioRecoverPasswordDTO data) {
        var usuario = service.reconverPassword(data);
        return ResponseEntity.ok(new UsuarioDetalhamentoDTO(usuario));
    }
}
