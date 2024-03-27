package org.example.notificationmodule.components;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @KafkaListener(topics = "send-email", groupId = "aaa")
    public void listenGroupAaa(String message) {
        System.out.println("Received Message in group aaa: " + message);
    }


}