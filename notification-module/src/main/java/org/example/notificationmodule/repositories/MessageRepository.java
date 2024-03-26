package org.example.notificationmodule.repositories;

import org.apache.kafka.common.protocol.types.Field;
import org.example.notificationmodule.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
