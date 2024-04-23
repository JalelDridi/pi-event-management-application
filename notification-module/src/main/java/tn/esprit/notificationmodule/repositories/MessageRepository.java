package tn.esprit.notificationmodule.repositories;

import tn.esprit.notificationmodule.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    long countByUserIdAndRead(String userId, boolean isRead);
}
