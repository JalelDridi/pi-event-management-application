package tn.esprit.notificationmodule.kafkaServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.services.EmailService;

import java.io.IOException;

@Component
public class Participation {

    @Autowired
    private EmailService emailService;
    private static final Logger LOG = LoggerFactory.getLogger(ConfirmUserRegistration.class);
    private static final String ERR_MSG_NOT_SENT = "Confirmation message not sent. Please verify your information.";

    @KafkaListener(topics = "participation", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) {

        if (notificationDto.getEmail() != null && notificationDto.getSubject() != null && notificationDto.getContent() != null) {
            emailService.sendEmail(notificationDto.getEmail(), notificationDto.getSubject(), notificationDto.getContent());
        }
        else {
            LOG.error(ERR_MSG_NOT_SENT);
        }
    }
}
