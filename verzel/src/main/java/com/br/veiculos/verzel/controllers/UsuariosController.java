package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.UsuarioDTO;
import com.br.veiculos.verzel.records.UsuarioDetalhamentoDTO;
import com.br.veiculos.verzel.records.UsuarioPasswordDTO;
import com.br.veiculos.verzel.services.UsuarioPermissoesService;
import com.br.veiculos.verzel.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("register/users")
public class UsuariosController {
    @Autowired
    private UsuariosService service;

    @Autowired
    private UsuarioPermissoesService usuarioPermissoesService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDetalhamentoDTO> createUser(@RequestBody UsuarioDTO data) {
        var user = service.createUser(data);
        usuarioPermissoesService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDetalhamentoDTO(user));
    }

    @GetMapping("/enviar-codigo")
    public ResponseEntity<String> recoverPassword(@RequestBody UsuarioPasswordDTO data) {
        var usuario = service.recoverPassword(data);
        return ResponseEntity.ok("Email enviado com sucesso para: " + usuario.getEmail());
    }


}
