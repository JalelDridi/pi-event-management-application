package org.example.notificationmodule.services;

import org.example.notificationmodule.entities.Message;
import org.example.notificationmodule.entities.Notification;
import org.example.notificationmodule.entities.Person;

import java.util.List;

public interface NotificationService {

    List<Notification> getNotificationsByUserId(Long userId);

    List<Notification> getReadNotificationsByUserId(Long userId);

    void addNotification(Notification notification, Message message);
}
