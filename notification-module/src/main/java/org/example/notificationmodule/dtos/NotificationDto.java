package org.example.notificationmodule.dtos;

import lombok.Data;
import org.example.notificationmodule.entities.Message;
import org.example.notificationmodule.entities.Notification;

import java.util.List;


@Data
public class NotificationDto {
    private Notification notification;
    private Message message;
}
