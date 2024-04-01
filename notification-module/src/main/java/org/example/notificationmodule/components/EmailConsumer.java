package org.example.notificationmodule.components;


import org.example.notificationmodule.dtos.NotificationDto;
import org.example.notificationmodule.entities.Message;
import org.example.notificationmodule.entities.Notification;
import org.example.notificationmodule.enums.DeliveryChannel;
import org.example.notificationmodule.services.EmailService;
import org.example.notificationmodule.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;


    @KafkaListener(topics = "send-email", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) {
        if (notificationDto != null && notificationDto.getEmail() != null && notificationDto.getMessage() != null) {
            final String TO = notificationDto.getEmail();
            final Message message = notificationDto.getMessage();
            final Notification notification = notificationDto.getNotification();

            if (message != null) {
                final String SUBJECT = message.getSubject();
                final String BODY = message.getContent();
                emailService.sendEmail(TO, SUBJECT, BODY);
            }

            if (notification != null) {
                notification.setDeliveryChannel(DeliveryChannel.email);
                notificationService.addNotification(notification, message);
            }
        } else {
            return;
        }
    }


}
