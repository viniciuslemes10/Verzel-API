package com.br.veiculos.verzel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class VerzelApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerzelApplication.class, args);
		Map<String, PasswordEncoder> codificar = new HashMap<>();
		Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(
				"", 8, 185000,
				Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		codificar.put("pbkdf2", pbkdf2PasswordEncoder);

		DelegatingPasswordEncoder senhaDecodificada = new DelegatingPasswordEncoder("pbkdf2", codificar);
		senhaDecodificada.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);
		String senha = senhaDecodificada.encode("admin123");
		System.out.println(senha);
	}

}
