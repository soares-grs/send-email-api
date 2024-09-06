package br.com.grs.send_email.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.password}")
    private String password;
    @Value("${email.address}")
    private String fromEmail;

    public void send(String to, String subject, String message) throws MessagingException {
        Session session = mountEmailSession();
        MimeMessage mail = mountMail(session, to, subject, message);
        Transport.send(mail);
    }

    private MimeMessage mountMail(Session session, String to, String subject, String message) throws MessagingException {
        MimeMessage mail = new MimeMessage(session);
        mail.setFrom(new InternetAddress(fromEmail));
        mail.setSubject(subject, "UTF-8");
        mail.setText(message, "UTF-8");
        mail.setSentDate(new Date());
        mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

        return mail;
    }

    private Session mountEmailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        return Session.getInstance(props, auth);
    }
}
