package com.br.veiculos.verzel.config;

import com.br.veiculos.verzel.security.JwtTokenFilter;
import com.br.veiculos.verzel.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> codificar = new HashMap<>();
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(
                "", 8, 185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        codificar.put("pbkdf2", pbkdf2PasswordEncoder);

        DelegatingPasswordEncoder senhaDecodificada = new DelegatingPasswordEncoder("pbkdf2", codificar);
        senhaDecodificada.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);
        return pbkdf2PasswordEncoder;
    }

    @Bean
    AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        JwtTokenFilter filtro = new JwtTokenFilter(tokenProvider);
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(filtro, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(
                                        "/register/**",
                                        "/auth/*",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/veiculos/admin/v1/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/veiculos/admin/v1/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/veiculos/admin/v1/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/**").authenticated()
                )
                .cors(cors -> {})
                .build();
    }
}
