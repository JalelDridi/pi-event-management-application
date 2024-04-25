package tn.esprit.notificationmodule.services;

import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getNotificationsByUserId(String userId);

    List<Notification> getAllNotifications();

    List<Notification> getReadNotificationsByUserId(String userId);

    long countUnreadNotifications(String userId);

    void addNotification(Notification notification, Message message);
}
