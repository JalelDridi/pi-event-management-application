package tn.esprit.notificationmodule.repositories;

import org.springframework.data.mongodb.repository.Query;
import tn.esprit.notificationmodule.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notificationmodule.enums.MessageType;


@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    Message getByMessageId(Long messageId);

    Message findByMessageId(Long messageId);
    long countByUserIdAndRead(String userId, boolean isRead);


    @Query("{'userId': ?0}")
    void updateMessagesSetIsReadToTrue(String userId, MessageType messageType);

}
