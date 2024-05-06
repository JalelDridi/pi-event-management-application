package tn.esprit.notificationmodule.repositories;

import tn.esprit.notificationmodule.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notificationmodule.enums.DeliveryChannel;

import java.util.List;


/**
 * Repository interface for managing Notification entities.
 */
@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    /**
     * Finds notifications by user ID.
     * @param userId The ID of the user.
     * @return A list of notifications for the specified user.
     */
    List<Notification> findNotificationByUserId(String userId);

    /**
     * Finds notifications by message ID.
     * @param messageId The ID of the message.
     * @return A list of notifications for the specified message.
     */
    List<Notification> findNotificationByMessageId(Long messageId);

    /**
     * Finds notifications by whether they are sent.
     * @param isSent Flag indicating if the notification is sent.
     * @return A list of notifications with the specified sent status.
     */
    List<Notification> findNotificationByIsSent(boolean isSent);

    /**
     * Finds notifications by user ID and read status.
     * @param userId The ID of the user.
     * @param isRead Flag indicating if the notification is read.
     * @return A list of notifications for the specified user and read status.
     */
    List<Notification> findNotificationByUserIdAndIsRead(String userId, boolean isRead);

    /**
     * Counts notifications by user ID, read status, and delivery channel.
     * @param userId The ID of the user.
     * @param isRead Flag indicating if the notification is read.
     * @param deliveryChannel The delivery channel of the notification.
     * @return The count of notifications matching the criteria.
     */
    Long countByUserIdAndIsReadAndDeliveryChannel(String userId, boolean isRead, DeliveryChannel deliveryChannel);

    /**
     * Finds notifications by user ID and delivery channel.
     * @param userId The ID of the user.
     * @param deliveryChannel The delivery channel of the notification.
     * @return A list of notifications for the specified user and delivery channel.
     */
    List<Notification> findNotificationByUserIdAndDeliveryChannel(String userId, DeliveryChannel deliveryChannel);
}