package tn.esprit.notificationmodule.dtos;

import lombok.Data;
import tn.esprit.notificationmodule.enums.DeliveryChannel;

@Data
public class NotificationDto {
    private String email;
    private String subject;
    private String content;
    private String userId;
    private DeliveryChannel deliveryChannel;
}
