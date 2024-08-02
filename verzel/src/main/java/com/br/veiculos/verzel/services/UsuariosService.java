package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.FailedToSendEmailException;
import com.br.veiculos.verzel.exceptions.InvalidCodeException;
import com.br.veiculos.verzel.exceptions.UsuarioEmailNotFoundException;
import com.br.veiculos.verzel.model.Usuarios;
import com.br.veiculos.verzel.records.UsuarioDTO;
import com.br.veiculos.verzel.records.UsuarioPasswordDTO;
import com.br.veiculos.verzel.records.UsuarioRecoverPasswordDTO;
import com.br.veiculos.verzel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UsuariosService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmailService emailService;

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

    public Usuarios sendCode(UsuarioPasswordDTO data) {
        var usuario = repository.findByUserEmail(data.email());

        if(usuario == null) {
            throw new UsuarioEmailNotFoundException("Email " + data.email() + " não encontrado!");
        }

        String codigo = generateRecoveryCode();
        try {
            emailService.sendCodePasswordRecovery(data.email(), usuario.getNomeCompleto(), codigo);
            usuario.setCodigo(codigo);
            return repository.save(usuario);
        } catch (IOException ex) {
            throw new FailedToSendEmailException("Falha ao enviar o email!");
        }
    }

    private String generateRecoveryCode() {
        return UUID.randomUUID().toString();
    }

    public Usuarios reconverPassword(UsuarioRecoverPasswordDTO data) {
        var usuario = repository.findByUserEmail(data.email());

        if(usuario == null) {
            throw new UsuarioEmailNotFoundException("Email " + data.email() + " não encontrado!");
        }

        if(data.code().equals(usuario.getCodigo())){
            usuario.setSenha(data.newPassword());
            encodePassword(usuario);
            usuario.setCodigo(null);
            return repository.save(usuario);
        }

        throw new InvalidCodeException("Código inválido: " + data.code());
    }
}
