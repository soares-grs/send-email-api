package br.com.grs.send_email.controller;

import br.com.grs.send_email.dto.EmailDto;
import br.com.grs.send_email.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    private ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDto) {
        try {
            emailService.send(emailDto.to(), emailDto.subject(), emailDto.message());
            return ResponseEntity.ok().body("Email successfully sent to " + emailDto.to());
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
