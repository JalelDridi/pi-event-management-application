package org.example.notificationmodule.services;

import org.springframework.messaging.MessagingException;

import java.util.List;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendEmailToMany(List<String> to, String subject, String body);
    void sendHtmlEmail(String receiverMail, String Subject, String htmlTemplate) throws MessagingException;

}
