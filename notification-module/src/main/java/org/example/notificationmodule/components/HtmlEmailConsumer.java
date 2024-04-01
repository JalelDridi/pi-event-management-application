package org.example.notificationmodule.components;

import org.example.notificationmodule.dtos.NotificationDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HtmlEmailConsumer {

    @KafkaListener(topics = "send-html-email", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) {
        // To be implemented : Logic to send HTML email
        System.out.println("Received HTML email: " + notificationDto);
    }
}
