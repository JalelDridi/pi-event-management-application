package tn.esprit.notificationmodule.components;

import tn.esprit.notificationmodule.dtos.UserNotifDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HtmlEmailConsumer {

    @KafkaListener(topics = "send-html-email", groupId = "aaa")
    public void listenGroupAaa(UserNotifDto userNotifDto) {
        // To be implemented : Logic to send HTML email
        System.out.println("Received HTML email: " + userNotifDto);
    }
}
