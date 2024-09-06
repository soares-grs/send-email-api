package br.com.grs.send_email.dto;

public record EmailDto(String to, String subject, String message) {
}
