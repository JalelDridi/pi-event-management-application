package tn.esprit.notificationmodule.dtos;

import lombok.Data;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;


@Data
public class NotificationDto {
    private String htmlBody; // Optional
    private String email; // Required
    private String subject;
    private String content;


}
