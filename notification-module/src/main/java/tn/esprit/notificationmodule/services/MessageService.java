package tn.esprit.notificationmodule.services;



import tn.esprit.notificationmodule.entities.Message;

import java.util.List;

/**
 * Service interface for managing messages.
 */
public interface MessageService {

    /**
     * Retrieves all chat messages.
     * @return A list of chat messages.
     */
    List<Message> getChatMessages();

    /**
     * Adds a new message.
     * @param message The message to add.
     */
    void addMessage(Message message);

    /**
     * Retrieves a message by its ID.
     * @param messageId The ID of the message to retrieve.
     * @return The message with the specified ID.
     */
    Message getMessageById(Long messageId);

    /**
     * Counts the number of unread messages.
     * @return The count of unread messages.
     */
    long countUnreadMessages();

    /**
     * Retrieves all messages.
     * @return A list of all messages.
     */
    List<Message> getAllMessages();

    /**
     * Marks the chat messages of a user as read.
     * @param userId The ID of the user whose chat messages will be marked as read.
     */
    void setUserChatMessagesAsRead(String userId);
}
