package tn.esprit.notificationmodule.kafkaServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;
import tn.esprit.notificationmodule.enums.DeliveryChannel;
import tn.esprit.notificationmodule.services.EmailService;
import tn.esprit.notificationmodule.services.NotificationService;

import java.io.IOException;
import java.util.Date;

/**
 * This component listens for upcoming events notifications and sends them to users via email.
 */
@Component
public class EmailUpcomingEvents {

    private final EmailService emailService;
    private final NotificationService notificationService;
    private static final Logger LOG = LoggerFactory.getLogger(EmailUpcomingEvents.class);
    private static final String ERR_MSG_NOT_SENT = "There is an error sending upcoming events newsletter to the user.";

    /**
     * Constructor for EmailUpcomingEvents class.
     * @param emailService The service responsible for sending emails.
     * @param notificationService The service responsible for managing notifications.
     */
    @Autowired
    public EmailUpcomingEvents(EmailService emailService, NotificationService notificationService) {
        this.emailService = emailService;
        this.notificationService = notificationService;
    }

    /**
     * Listens for upcoming events notifications and sends them to users via email.
     * @param notificationDto The notification data transfer object.
     */
    @KafkaListener(topics = "upcoming-events", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) {
        try {
            // Check if the required fields are not null
            if (notificationDto.getEmail() != null && notificationDto.getSubject() != null && notificationDto.getContent() != null) {
                // Instantiate objects
                Message message = new Message();
                Notification notification = new Notification();

                // Send email notification
                emailService.sendHtmlEmail(notificationDto.getEmail(), notificationDto.getSubject(), notificationDto.getContent());

                // Set notification properties
                notification.setDeliveryChannel(DeliveryChannel.email);
                notification.setIsRead(true);
                notification.setIsSent(true);

                // Set message properties
                message.setSentDate(new Date());
                message.setContent(notificationDto.getContent());
                message.setSubject(notificationDto.getSubject());

                // Store the notification and message in the database
                notificationService.addNotification(notification, message);
            } else {
                LOG.error(ERR_MSG_NOT_SENT);
            }
        } catch (Exception e) {
            LOG.error("Error processing upcoming events notification: {}", e.getMessage());
        }
    }
}

