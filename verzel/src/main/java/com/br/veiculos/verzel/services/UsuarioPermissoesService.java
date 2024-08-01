package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.PermissaoNotFoundException;
import com.br.veiculos.verzel.model.UsuarioPermissao;
import com.br.veiculos.verzel.model.UsuarioPermissaoId;
import com.br.veiculos.verzel.model.Usuarios;
import com.br.veiculos.verzel.repository.PermissoesRepository;
import com.br.veiculos.verzel.repository.UsuarioPermissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioPermissoesService {
    @Autowired
    private UsuarioPermissoesRepository repository;

    @Autowired
    private PermissoesRepository permissoesRepository;

    private final Long PERMISSAO_COMMON = 2L;


    public void create(Usuarios user) {
        var permissao = permissoesRepository.findById(PERMISSAO_COMMON).orElseThrow(
                () -> new PermissaoNotFoundException("Permissão não encontrada")
        );
        UsuarioPermissaoId id = new UsuarioPermissaoId(user.getId(), permissao.getId());
        var usuarioPermissao = new UsuarioPermissao(user, permissao);
        usuarioPermissao.setId(id);
        repository.save(usuarioPermissao);
    }
}
