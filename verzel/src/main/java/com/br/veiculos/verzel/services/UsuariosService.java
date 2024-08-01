package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.UsuarioEmailNotFoundException;
import com.br.veiculos.verzel.model.Usuarios;
import com.br.veiculos.verzel.records.UsuarioDTO;
import com.br.veiculos.verzel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuariosService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;


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
        encodePassword(usuario);
        return repository.save(usuario);
    }

    private Usuarios encodePassword(Usuarios usuario) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(
                "", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2PasswordEncoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);
        String newSenha = passwordEncoder.encode(usuario.getSenha());

        if(newSenha.contains("{pbkdf2}")) {
            newSenha = newSenha.substring("{pbkdf2}".length());
        }
        usuario.setSenha(newSenha);
        return usuario;
    }
}
