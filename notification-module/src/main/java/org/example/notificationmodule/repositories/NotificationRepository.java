package org.example.notificationmodule.repositories;

import org.example.notificationmodule.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findNotificationByUserId(Long userId);

    List<Notification> findNotificationByUserIdAndIsRead(Long userId, boolean isRead);

    Long countByUserIdAndIsRead(Long userId, boolean isRead);


    // Save method to save or update Notification entity
    @Override
    <S extends Notification> S save(S entity);
}
