package tn.esprit.notificationmodule.components;


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

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;

    private static final Logger LOG = LoggerFactory.getLogger(EmailConsumer.class);
    private static final String ERR_MSG_NOT_SENT = "Message not sent, please try again !";


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

            notification.setDeliveryChannel(DeliveryChannel.email);
            notificationService.addNotification(notificationDto.getNotification(), message);
        } else {
            LOG.error(ERR_MSG_NOT_SENT);
        }
    }


}
