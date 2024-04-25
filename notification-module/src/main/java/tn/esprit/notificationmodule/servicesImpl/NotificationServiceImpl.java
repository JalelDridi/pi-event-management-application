package tn.esprit.notificationmodule.servicesImpl;

import jakarta.annotation.Resource;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;
import tn.esprit.notificationmodule.enums.DeliveryChannel;
import tn.esprit.notificationmodule.repositories.MessageRepository;
import tn.esprit.notificationmodule.repositories.NotificationRepository;
import tn.esprit.notificationmodule.services.NotificationService;
import tn.esprit.notificationmodule.services.SequenceGeneratorService;
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
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationRepository.findNotificationByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getReadNotificationsByUserId(String userId) {
        return notificationRepository.findNotificationByUserIdAndIsRead(userId, true);
    }

    @Override
    public long countUnreadNotifications(String userId) {
        return notificationRepository.countByUserIdAndIsReadAndDeliveryChannel(userId, false, DeliveryChannel.webNotification);
    }

    @Override
    public void addNotification(Notification notification, Message message) {
        message.setMessageId(sequenceGeneratorService.generateSequence(Message.SEQUENCE_NAME));
        message.setRead(false);
        messageRepository.save(message);

        notification.setMessageId(message.getMessageId());
        notification.setNotificationId(sequenceGeneratorService.generateSequence(Notification.SEQUENCE_NAME));
        notification.setIsRead(false);
        notificationRepository.save(notification);
    }




}
