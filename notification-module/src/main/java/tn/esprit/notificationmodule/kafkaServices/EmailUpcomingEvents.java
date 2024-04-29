package tn.esprit.notificationmodule.kafkaServices;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.notificationmodule.dtos.NotificationDto;

import java.io.IOException;

@Component
public class EmailUpcomingEvents {

    @KafkaListener(topics = "upcoming-events", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) throws IOException {
    }
}
