package tn.esprit.notificationmodule.kafkaServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.enums.MessageType;
import tn.esprit.notificationmodule.services.MessageService;

import java.io.IOException;
import java.util.Date;

@Component
public class SendMessage {


    @Autowired
    private MessageService messageService;

    @KafkaListener(topics = "send-message", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) throws IOException {
        String groupId = notificationDto.getGroupId();

        Message message = new Message();
        message.setSubject(notificationDto.getSubject());
        message.setContent(notificationDto.getContent());
        message.setUserIdFrom(notificationDto.getUserIdFrom());
        message.setRead(false);
        message.setSentDate(new Date());


        if (groupId == null) {
            message.setUserId(notificationDto.getUserId());
            message.setMessageType(MessageType.chatMessage);
        }
        else {
            message.setGroupId(notificationDto.getGroupId());
            message.setMessageType(MessageType.groupMessage);
        }

        messageService.addMessage(message);
    }
}
