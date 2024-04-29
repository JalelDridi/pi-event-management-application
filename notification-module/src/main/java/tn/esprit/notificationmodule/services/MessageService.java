package tn.esprit.notificationmodule.services;



import tn.esprit.notificationmodule.entities.Message;

import java.util.List;

public interface MessageService {


    void addMessage(Message message);

    Message getMessageById(Long messageId);
    long countUnreadMessages(String userId);

    List<Message> getAllMessages();

    void setUserMessagesAsRead(String userId);
}
