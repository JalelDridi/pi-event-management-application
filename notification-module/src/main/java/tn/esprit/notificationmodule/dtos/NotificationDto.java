package tn.esprit.notificationmodule.dtos;

import lombok.Data;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;


@Data
public class NotificationDto {
    private String htmlBody;
    private String email;
    private Notification notification;
    private Message message;
}
