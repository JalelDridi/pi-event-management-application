package tn.esprit.notificationmodule.repositories;

import tn.esprit.notificationmodule.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notificationmodule.enums.DeliveryChannel;

import java.util.List;


@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findNotificationByUserId(String userId);

    List<Notification> findNotificationByIsSent(boolean isSent);
    List<Notification> findNotificationByUserIdAndIsRead(String userId, boolean isRead);

    Long countByUserIdAndIsReadAndDeliveryChannel(String userId, boolean isRead, DeliveryChannel deliveryChannel);

    List<Notification> findNotificationByUserIdAndDeliveryChannel(String userId, DeliveryChannel deliveryChannel);


}
