package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.UsuarioEmailNotFoundException;
import com.br.veiculos.verzel.model.Usuarios;
import com.br.veiculos.verzel.records.UsuarioDTO;
import com.br.veiculos.verzel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = repository.findByUserEmail(email);

        if(usuario != null) {
            return usuario;
        }
        throw new UsuarioEmailNotFoundException("Email " + email + " não encontrado!");
    }

    public Usuarios createUser(UsuarioDTO data) {
        var usuario = new Usuarios(data);
        usuario.setSenha(passwordEncoder.encode(data.senha()));
        return repository.save(usuario);
    }
}
