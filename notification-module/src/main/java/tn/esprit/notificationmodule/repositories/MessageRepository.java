package tn.esprit.notificationmodule.repositories;

import org.springframework.data.mongodb.repository.Query;
import tn.esprit.notificationmodule.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notificationmodule.enums.MessageType;

import java.util.List;


@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    Message getByMessageId(Long messageId);
    Message findByMessageId(Long messageId);
    long countMessageByMessageTypeAndIsRead(MessageType messageType, boolean read);
    @Query("{'userId': ?0}")
    void updateMessagesSetIsReadToTrue(String userId, MessageType messageType);
    List<Message> findMessageByMessageType(MessageType messageType);

}
