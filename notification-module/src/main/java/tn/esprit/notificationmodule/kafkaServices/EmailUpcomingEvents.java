package tn.esprit.notificationmodule.kafkaServices;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.notificationmodule.dtos.UserNotifDto;

import java.io.IOException;

@Component
public class EmailUpcomingEvents {

    @KafkaListener(topics = "upcoming-events", groupId = "aaa")
    public void listenGroupAaa(UserNotifDto userNotifDto) throws IOException {
    }
}
