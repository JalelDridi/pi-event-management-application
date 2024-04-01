package org.example.notificationmodule.dtos;

import lombok.Data;
import org.example.notificationmodule.entities.Message;
import org.example.notificationmodule.entities.Notification;



@Data
public class NotificationDto {
    private String htmlBody;
    private String email;
    private Notification notification;
    private Message message;
}
