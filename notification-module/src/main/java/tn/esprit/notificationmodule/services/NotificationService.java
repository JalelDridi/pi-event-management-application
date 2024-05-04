package tn.esprit.notificationmodule.services;

import org.springframework.http.HttpHeaders;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;

import java.util.List;

public interface NotificationService {



    List<Message> getWebNotifications(String userId);

    List<Notification> getNotificationsByUserId(String userId);

    List<Notification> getAllNotifications();

    List<Notification> getReadNotificationsByUserId(String userId);

    long countUnreadNotifications(String userId);

    void addNotification(Notification notification, Message message);


    void confirmEventParticipation(String userId, Long eventId, HttpHeaders headers);

    void setUserNotificationsAsRead(Long[] messageIds);

    void deleteNotification(Long id);

    void sendWebNotifications(NotificationDto notificationDto);

}
