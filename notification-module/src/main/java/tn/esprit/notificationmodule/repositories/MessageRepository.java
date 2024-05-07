package tn.esprit.notificationmodule.repositories;

import org.springframework.data.mongodb.repository.Query;
import tn.esprit.notificationmodule.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notificationmodule.enums.MessageType;

import java.util.List;


/**
 * Repository interface for managing Message entities.
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * Retrieves a message by its message ID.
     * @param messageId The ID of the message.
     * @return The message object.
     */
    Message getByMessageId(Long messageId);

    /**
     * Finds a message by its message ID.
     * @param messageId The ID of the message.
     * @return The message object.
     */
    Message findByMessageId(Long messageId);

    /**
     * Counts messages by message type and whether they are read.
     * @param messageType The type of message.
     * @param read Flag indicating if the message is read.
     * @return The count of messages.
     */
    long countMessageByMessageTypeAndIsRead(MessageType messageType, boolean read);

    /**
     * Updates messages for a user to mark them as read.
     * @param userId The ID of the user.
     * @param messageType The type of message.
     */
    @Query("{'userId': ?0}")
    void updateMessagesSetIsReadToTrue(String userId, MessageType messageType);

    /**
     * Finds messages by message type.
     * @param messageType The type of message.
     * @return A list of messages with the specified type.
     */
    List<Message> findMessageByMessageType(MessageType messageType);
}
