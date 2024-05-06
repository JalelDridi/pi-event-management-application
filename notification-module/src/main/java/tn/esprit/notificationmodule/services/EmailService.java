package tn.esprit.notificationmodule.services;


import org.springframework.messaging.MessagingException;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for sending emails.
 */
public interface EmailService {

    /**
     * Sends a plain text email.
     * @param to The recipient's email address.
     * @param subject The subject of the email.
     * @param body The body content of the email.
     */
    void sendEmail(String to, String subject, String body);

    /**
     * Sends an email and a web notification containing upcoming events.
     */
    void sendUpcomingEvents();

    /**
     * Sends an HTML email.
     * @param receiverMail The recipient's email address.
     * @param subject The subject of the email.
     * @param htmlTemplate The HTML content of the email.
     * @throws MessagingException If an error occurs during message creation.
     */
    void sendHtmlEmail(String receiverMail, String subject, String htmlTemplate) throws MessagingException;

    /**
     * Loads the HTML template for activating an account.
     * @param username The username of the account to be activated.
     * @param activationCode The activation code for the account.
     * @return The HTML template content.
     */
    String loadActivateAccountTemplate(String username, String activationCode) throws IOException;

    /**
     * Loads the HTML template for displaying upcoming events.
     * @param notificationEventDtos The list of upcoming events to be displayed in the template.
     * @return The HTML template content with the upcoming events.
     */
    String loadUpcomingEventsTemplate(List<NotificationEventDto> notificationEventDtos);
}