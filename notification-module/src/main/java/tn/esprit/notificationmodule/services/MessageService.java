package tn.esprit.notificationmodule.services;



import tn.esprit.notificationmodule.entities.Message;

import java.util.List;

public interface MessageService {


    List<Message> getChatMessages();
    void addMessage(Message message);
    Message getMessageById(Long messageId);
    long countUnreadMessages();

    List<Message> getAllMessages();

    void setUserChatMessagesAsRead(String userId);
}
