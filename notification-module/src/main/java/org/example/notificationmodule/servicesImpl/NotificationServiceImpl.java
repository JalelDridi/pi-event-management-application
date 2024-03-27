package org.example.notificationmodule.servicesImpl;

import org.example.notificationmodule.entities.Message;
import org.example.notificationmodule.entities.Notification;
import org.example.notificationmodule.entities.Person;
import org.example.notificationmodule.repositories.MessageRepository;
import org.example.notificationmodule.repositories.NotificationRepository;
import org.example.notificationmodule.services.NotificationService;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private MessageRepository messageRepository;

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findNotificationByUserId(userId);
    }

    @Override
    public List<Notification> getReadNotificationsByUserId(Long userId) {
        return notificationRepository.findNotificationByUserIdAndIsRead(userId, true);
    }

    @Override
    public void addNotification(Notification notification, Message message) {
        messageRepository.save(message);
        notification.setMessageId(message.getMessageId());
        notificationRepository.save(notification);
    }
}
