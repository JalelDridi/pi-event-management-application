package tn.esprit.notificationmodule.kafkaServices;


import tn.esprit.notificationmodule.dtos.UserNotifDto;
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

    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;
    private static final Logger LOG = LoggerFactory.getLogger(ConfirmUserRegistration.class);
    private static final String ERR_MSG_NOT_SENT = "Confirmation message not sent. Please verify your information.";



    @KafkaListener(topics = "confirm-user-registration", groupId = "aaa")
    public void listenGroupAaa(UserNotifDto userNotifDto) throws IOException {
        if (userNotifDto.getEmail() != null && userNotifDto.getSubject() != null && userNotifDto.getContent() != null) {
            // Instantiate objects:
            Message message = new Message();
            Notification notification = new Notification();

            // Send Confirmation message:
            String htmlBody = emailService.loadEmailConfirmationTemplate(userNotifDto.getFullName(), userNotifDto.getContent());
            emailService.sendHtmlEmail(userNotifDto.getEmail(), userNotifDto.getSubject(), htmlBody);
            // Set notification properties:
            notification.setDeliveryChannel(DeliveryChannel.email);
            notification.setIsRead(true);
            notification.setIsSent(true);
            // Set message properties:
            message.setSentDate(new Date());
            message.setContent(userNotifDto.getContent());
            message.setSubject(userNotifDto.getSubject());

            // Store the notification and the message in the database ( for 2 purposes : 1. data collection | 2. Schedule unsent mails for later ...
            notificationService.addNotification(notification, message);
        } else {
            LOG.error(ERR_MSG_NOT_SENT);
        }
    }


}
