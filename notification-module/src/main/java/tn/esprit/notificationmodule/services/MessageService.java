package tn.esprit.notificationmodule.services;


import tn.esprit.notificationmodule.entities.Message;

import java.util.List;

public interface MessageService {

    long countUnreadMessages(String userId);

    List<Message> getAllMessages();
}
