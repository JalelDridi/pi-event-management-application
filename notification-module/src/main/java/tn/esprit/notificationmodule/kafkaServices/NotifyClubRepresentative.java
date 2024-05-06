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
import tn.esprit.notificationmodule.enums.MessageType;
import tn.esprit.notificationmodule.services.EmailService;
import tn.esprit.notificationmodule.services.NotificationService;

import java.io.IOException;
import java.util.Date;

@Component
public class NotifyClubRepresentative {

    private final EmailService emailService;
    private final NotificationService notificationService;
    private static final Logger LOG = LoggerFactory.getLogger(NotifyClubRepresentative.class);

    /**
     * Constructs a new NotifyClubRepresentative instance with the specified EmailService and NotificationService.
     * @param emailService The email service to use for sending emails.
     * @param notificationService The notification service to use for adding notifications along with their content (message).
     */
    @Autowired
    public NotifyClubRepresentative(EmailService emailService, NotificationService notificationService) {
        this.emailService = emailService;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "notify-club-representative", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) {
        try {
            Notification notification = new Notification();
            Message message = new Message();

            // Set notification properties
            notification.setIsSent(true);
            notification.setUserId(notificationDto.getUserId());
            notification.setDeliveryChannel(DeliveryChannel.webNotification);

            // Set message properties
            message.setSubject(notificationDto.getSubject());
            message.setContent(notificationDto.getContent());
            message.setUserId(notificationDto.getUserId());
            message.setMessageType(MessageType.webNotification);
            message.setSentDate(new Date());

            // Add notification to the database
            notificationService.addNotification(notification, message);

            // If email address is provided, send email notification
            if (notificationDto.getEmail() != null) {
                notification.setDeliveryChannel(DeliveryChannel.email);
                message.setMessageType(MessageType.email);
                emailService.sendHtmlEmail(notificationDto.getEmail(), message.getSubject(), message.getContent());
                notificationService.addNotification(notification, message);
            }
        } catch (Exception e) {
            LOG.error("Error processing notification: {}", e.getMessage());
        }
    }
}
