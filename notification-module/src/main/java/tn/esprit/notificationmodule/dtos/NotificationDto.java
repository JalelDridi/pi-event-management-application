package tn.esprit.notificationmodule.dtos;

import lombok.Data;
import tn.esprit.notificationmodule.enums.DeliveryChannel;

import java.io.Serializable;


@Data
public class NotificationDto implements Serializable {
    private String fullName;
    private String email;
    private String subject; // can take many forms : mail subject, event description .....
    private String content; // can take many forms : mail content, message content ....
    private String userId; // TO
    private String groupId; // TO or FROM
    // NOTE : in group messaging we'll have both userIdFrom and groupIdFrom
    private String userIdFrom; // In case it is a message from a single person
    private DeliveryChannel deliveryChannel; // Can either be : mail, web notification
}
