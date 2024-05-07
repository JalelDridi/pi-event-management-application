package tn.esprit.notificationmodule.services;

import org.springframework.http.HttpHeaders;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;

import java.util.List;

/**
 * Service interface for managing notifications.
 */
public interface NotificationService {

    /**
     * Retrieves web notifications for a given user.
     * @param userId The ID of the user.
     * @return A list of web notifications.
     */
    List<Message> getWebNotifications(String userId);

    /**
     * Retrieves notifications for a given user.
     * @param userId The ID of the user.
     * @return A list of notifications.
     */
    List<Notification> getNotificationsByUserId(String userId);

    /**
     * Retrieves all notifications.
     * @return A list of all notifications.
     */
    List<Notification> getAllNotifications();

    /**
     * Retrieves read notifications for a given user.
     * @param userId The ID of the user.
     * @return A list of read notifications.
     */
    List<Notification> getReadNotificationsByUserId(String userId);

    /**
     * Counts the number of unread notifications for a given user.
     * @param userId The ID of the user.
     * @return The count of unread notifications.
     */
    long countUnreadNotifications(String userId);

    /**
     * Adds a new notification and message to the database.
     * @param notification The notification to add.
     * @param message The message associated with the notification.
     */
    void addNotification(Notification notification, Message message);

    /**
     * Confirms event participation for a user.
     * @param userId The ID of the user.
     * @param eventId The ID of the event.
     * @param headers The HTTP headers.
     */
    void confirmEventParticipation(String userId, Long eventId, HttpHeaders headers);

    /**
     * Marks user notifications as read.
     * @param messageIds The IDs of the notifications to mark as read.
     */
    void setUserNotificationsAsRead(Long[] messageIds);

    /**
     * Deletes a notification.
     * @param id The ID of the notification to delete.
     */
    void deleteNotification(Long id);

    /**
     * Sends web notifications.
     * @param notificationDto The notification DTO.
     */
    void sendWebNotifications(NotificationDto notificationDto);

}