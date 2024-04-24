package tn.esprit.notificationmodule.dtos;

import lombok.Data;


@Data
public class NotificationDto {
    private String email;
    private String subject;
    private String content;
    private String userId;
}
