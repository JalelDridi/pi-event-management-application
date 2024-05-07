package tn.esprit.notificationmodule.kafkaServices;


import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;
import tn.esprit.notificationmodule.enums.DeliveryChannel;
import tn.esprit.notificationmodule.services.EmailService;
import tn.esprit.notificationmodule.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class ConfirmUserRegistration {

    private final EmailService emailService;
    private final NotificationService notificationService;
    private static final Logger LOG = LoggerFactory.getLogger(ConfirmUserRegistration.class);
    private static final String ERR_MSG_NOT_SENT = "Confirmation message not sent. Please verify your information.";

    @Autowired
    public ConfirmUserRegistration(EmailService emailService, NotificationService notificationService) {
        this.emailService = emailService;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "confirm-user-registration", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) {
        try {
            // Check if the required fields are not null
            if (notificationDto.getEmail() != null && notificationDto.getSubject() != null && notificationDto.getContent() != null) {
                // Instantiate objects
                Message message = new Message();
                Notification notification = new Notification();

                // Send confirmation email
                String htmlBody = emailService.loadActivateAccountTemplate(notificationDto.getFullName(), notificationDto.getContent());
                emailService.sendHtmlEmail(notificationDto.getEmail(), notificationDto.getSubject(), htmlBody);

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
            LOG.error("Error processing confirmation notification: {}", e.getMessage());
        }
    }
}
