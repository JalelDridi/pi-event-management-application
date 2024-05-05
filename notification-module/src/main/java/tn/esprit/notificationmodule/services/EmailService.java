package tn.esprit.notificationmodule.services;


import org.springframework.messaging.MessagingException;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;

import java.io.IOException;
import java.util.List;

public interface EmailService {

    void sendEmail(String to, String subject, String body);
    void sendUpcomingEvents();
    void sendHtmlEmail(String receiverMail, String Subject, String htmlTemplate) throws MessagingException;
    String loadActivateAccountTemplate(String username, String activationCode) throws IOException;
    String loadUpcomingEventsTemplate(List<NotificationEventDto> notificationEventDtos);

}
