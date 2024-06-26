package tn.esprit.notificationmodule.servicesImpl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.enums.MessageType;
import tn.esprit.notificationmodule.repositories.MessageRepository;
import tn.esprit.notificationmodule.services.MessageService;
import tn.esprit.notificationmodule.services.SequenceGeneratorService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Resource
    private MessageRepository messageRepository;


    @Override
    public List<Message> getChatMessages() {
        return messageRepository.findMessageByMessageType(MessageType.chatMessage);
    }

    @Override
    public void addMessage(Message message) {
        message.setMessageId(sequenceGeneratorService.generateSequence(Message.SEQUENCE_NAME));
        messageRepository.save(message);
    }

    @Override
    public Message getMessageById(Long messageId) {
        return messageRepository.getByMessageId(messageId);
    }

    @Override
    public long countUnreadMessages() {
        return messageRepository.countMessageByMessageTypeAndIsRead(MessageType.chatMessage, false);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void setUserChatMessagesAsRead(String userId) {
        messageRepository.updateMessagesSetIsReadToTrue(userId, MessageType.chatMessage);
    }
}
