package tn.esprit.notificationmodule.servicesImpl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.repositories.MessageRepository;
import tn.esprit.notificationmodule.services.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepository messageRepository;


    @Override
    public Message getMessageById(Long messageId) {
        return messageRepository.getByMessageId(messageId);
    }

    @Override
    public long countUnreadMessages(String userId) {
        return messageRepository.countByUserIdAndRead(userId, false);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
