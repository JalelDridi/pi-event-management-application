package tn.esprit.notificationmodule.kafkaServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.services.EmailService;
import tn.esprit.notificationmodule.services.NotificationService;

import java.io.IOException;

@Component
public class NotifyAdmin {

    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;
    private static final Logger LOG = LoggerFactory.getLogger(ConfirmUserRegistration.class);

    @KafkaListener(topics = "notify-admin", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) throws IOException {
        // TO DO : implement the terrible restTemplate to get the list of admins
    }
}
