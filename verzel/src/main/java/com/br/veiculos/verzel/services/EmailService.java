package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.FailedToSendEmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public String sendCodePasswordRecovery(String email, String nome, String codigo) throws IOException {
        String assunto = "Recuperação de Senha";
        String html = loadHtmlExternal("templates/codigo-recuperar-senha.html");

        html = html.replace("{{nome}}", nome)
                .replace("{{codigo}}", codigo);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setTo(email);
            helper.setSubject(assunto);
            helper.setText(html, true);
            javaMailSender.send(message);

            return codigo;
        } catch (MailSendException | MessagingException e) {
            throw new FailedToSendEmailException("Falha ao enviar o email!");
        }
    }

    private String loadHtmlExternal(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
