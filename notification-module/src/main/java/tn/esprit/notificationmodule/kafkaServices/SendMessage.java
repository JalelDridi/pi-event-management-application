package tn.esprit.notificationmodule.kafkaServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.enums.MessageType;
import tn.esprit.notificationmodule.services.MessageService;

import java.io.IOException;
import java.util.Date;

/**
 * This component listens for messages and saves them in the database.
 */
@Component
public class SendMessage {

    private final MessageService messageService;
    private static final Logger LOG = LoggerFactory.getLogger(SendMessage.class);

    /**
     * Constructor for SendMessage class.
     * @param messageService The service responsible for managing messages.
     */
    @Autowired
    public SendMessage(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Listens for messages and saves them in the database.
     * @param notificationDto The notification data transfer object.
     */
    @KafkaListener(topics = "send-message", groupId = "aaa")
    public void listenGroupAaa(NotificationDto notificationDto) {
        try {
            String groupId = notificationDto.getGroupId();

            // Create a new message object
            Message message = new Message();
            message.setSubject(notificationDto.getSubject());
            message.setContent(notificationDto.getContent());
            message.setUserIdFrom(notificationDto.getUserIdFrom());
            message.setRead(false);
            message.setSentDate(new Date());

            // Set message type based on whether it's a group message or chat message
            if (groupId == null) {
                message.setUserId(notificationDto.getUserId());
                message.setMessageType(MessageType.chatMessage);
            } else {
                message.setGroupId(groupId);
                message.setMessageType(MessageType.groupMessage);
            }

            // Add the message to the database
            messageService.addMessage(message);
        } catch (Exception e) {
            LOG.error("Error processing message: {}", e.getMessage());
        }
    }
}