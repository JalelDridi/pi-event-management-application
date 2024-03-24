package org.example.notificationmodule.services;

import org.springframework.messaging.MessagingException;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendHtmlEmail() throws MessagingException;
}
