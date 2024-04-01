package org.example.notificationmodule.servicesImpl;

import jakarta.annotation.Resource;
import org.example.notificationmodule.entities.Message;
import org.example.notificationmodule.entities.Notification;
import org.example.notificationmodule.repositories.MessageRepository;
import org.example.notificationmodule.repositories.NotificationRepository;
import org.example.notificationmodule.services.NotificationService;
import org.example.notificationmodule.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Resource
    private NotificationRepository notificationRepository;
    @Resource
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
    public Long countUnreadNotifications(Long userId) {
        return notificationRepository.countByUserIdAndIsRead(userId, false);
    }

    @Override
    public void addNotification(Notification notification, Message message) {
        message.setMessageId(sequenceGeneratorService.generateSequence(Message.SEQUENCE_NAME));
        messageRepository.save(message);
        notification.setMessageId(message.getMessageId());
        notification.setNotificationId(sequenceGeneratorService.generateSequence(Notification.SEQUENCE_NAME));
        notification.setIsRead(false);
        notificationRepository.save(notification);
    }




}
