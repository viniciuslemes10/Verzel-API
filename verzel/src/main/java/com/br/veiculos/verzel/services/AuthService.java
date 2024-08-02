package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.UsuarioEmailNotFoundException;
import com.br.veiculos.verzel.records.vo.AccountCredentialsVO;
import com.br.veiculos.verzel.records.vo.TokenVO;
import com.br.veiculos.verzel.repository.UsuarioRepository;
import com.br.veiculos.verzel.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UsuarioRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity login(AccountCredentialsVO data) {
        try {
            var email = data.getEmail();
            var senha = data.getSenha();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));

            var usuario = userRepository.findByUserEmail(email);

            var tokenResponse = new TokenVO();
            if(usuario != null) {
                tokenResponse = tokenProvider.createAccessToken(email, usuario.getRoles());
            } else {
                throw new UsuarioEmailNotFoundException("Usuário não encontrado com email do usuário: " + email);
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email de usuário ou senha inválidos!");
        }
    }
}
