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
        final String TO = notificationDto.getEmail();
        final String SUBJECT = notificationDto.getMessage().getSubject();
        final String BODY = notificationDto.getMessage().getContent();
        emailService.sendEmail(TO, SUBJECT, BODY);

        final Notification notification = notificationDto.getNotification();
        final Message message = notificationDto.getMessage();


        notification.setDeliveryChannel(DeliveryChannel.email);
        notificationService.addNotification(notification, message);

    }


}
