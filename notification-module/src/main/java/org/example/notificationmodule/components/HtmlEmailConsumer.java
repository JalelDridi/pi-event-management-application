package org.example.notificationmodule.components;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HtmlEmailConsumer {

    @KafkaListener(topics = "html-email-topic", groupId = "aaa")
    public void listenGroupAaa(String htmlContent) {
        // To be implemented : Logic to send HTML email
        System.out.println("Received HTML email: " + htmlContent);
    }
}
