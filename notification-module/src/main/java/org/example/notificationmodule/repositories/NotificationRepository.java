package org.example.notificationmodule.repositories;

import org.example.notificationmodule.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
