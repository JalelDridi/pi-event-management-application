package tn.esprit.notificationmodule.services;

import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getNotificationsByUserId(Long userId);

    List<Notification> getReadNotificationsByUserId(Long userId);

    Long countUnreadNotifications(Long userId);

    void addNotification(Notification notification, Message message);
}
