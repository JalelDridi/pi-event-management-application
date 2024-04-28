package tn.esprit.notificationmodule.services;

import org.springframework.messaging.MessagingException;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;

import java.io.IOException;
import java.util.List;

public interface EmailService {

    void sendEmail(String to, String subject, String body);


    List<NotificationEventDto> sendUpcomingEvents(NotificationEventDto notificationEventDto);
    void sendEmailToMany(List<String> to, String subject, String body);
    void sendHtmlEmail(String receiverMail, String Subject, String htmlTemplate) throws MessagingException;
    String loadEmailConfirmationTemplate(String username, String activationCode) throws IOException;

    void emailUpcomingEvents();
}
