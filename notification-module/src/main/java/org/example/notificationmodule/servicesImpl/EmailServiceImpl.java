package org.example.notificationmodule.servicesImpl;


import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.example.notificationmodule.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public void sendHtmlEmail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setFrom(new InternetAddress("ramdhaniahmedamine@gmail.com"));
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setRecipients(MimeMessage.RecipientType.TO, "ahmedamine.romdnani@esprit.tn");
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setSubject("Test email from Spring");
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";
        try {
            message.setContent(htmlContent, "text/html; charset=utf-8");
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);
    }
}